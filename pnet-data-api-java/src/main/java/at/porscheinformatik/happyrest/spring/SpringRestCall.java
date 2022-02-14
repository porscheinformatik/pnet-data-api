package at.porscheinformatik.happyrest.spring;

import static at.porscheinformatik.happyrest.spring.SpringRestUtils.*;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode;
import org.springframework.web.util.UriBuilder;

import at.porscheinformatik.happyrest.AbstractRestCall;
import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestException;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestHeader;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestParameter;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestResponseException;
import at.porscheinformatik.happyrest.RestVariable;

/**
 * A REST call. This implementation is thread-safe!
 *
 * @author ham
 */
public class SpringRestCall extends AbstractRestCall
{
    private final RestTemplate restTemplate;
    private final RestLoggerAdapter loggerAdapter;

    protected SpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter)
    {
        this(restTemplate, loggerAdapter, null, null, MediaType.APPLICATION_JSON, null, formatter, null);
    }

    protected SpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter,
        String url)
    {
        this(restTemplate, loggerAdapter, url, null, MediaType.APPLICATION_JSON, null, formatter, null);
    }

    protected SpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, String url,
        List<MediaType> acceptableMediaTypes, MediaType contentType, List<RestAttribute> attributes,
        RestFormatter formatter, Object body)
    {
        super(url, acceptableMediaTypes, contentType, attributes, formatter, body);

        this.restTemplate = restTemplate;
        this.loggerAdapter = loggerAdapter;
    }

    @Override
    protected RestCall copy(String url, List<MediaType> acceptableMediaTypes, MediaType contentType,
        List<RestAttribute> attributes, RestFormatter formatter, Object body)
    {
        return new SpringRestCall(restTemplate, loggerAdapter, url, acceptableMediaTypes, contentType, attributes,
            formatter, body);
    }

    @Override
    public Object getBody()
    {
        Object body = super.getBody();

        if (body != null)
        {
            return body;
        }

        if (isForm())
        {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

            getParameters()
                .stream()
                .forEach(attribute -> map.add(attribute.getName(), format(MediaType.TEXT_PLAIN, attribute.getValue())));

            return map;
        }

        return body;
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException
    {
        boolean form = verify(method);

        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, form);
        HttpEntity<Object> entity = new HttpEntity<>(getBody(), headers);

        loggerAdapter.logRequest(method, String.valueOf(uri));

        try
        {
            return new SpringRestResponse<>(restTemplate.exchange(uri, toHttpMethod(method), entity, responseType));
        }
        catch (RestClientResponseException e)
        {
            throw new RestResponseException(toDescription(method, uri, e), e.getRawStatusCode(), e.getStatusText(), e);
        }
        catch (Exception e)
        {
            throw new RestException(method + " " + uri, e);
        }
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException
    {
        boolean form = verify(method);

        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, form);
        HttpEntity<Object> entity = new HttpEntity<>(getBody(), headers);

        loggerAdapter.logRequest(method, String.valueOf(uri));

        try
        {
            return new SpringRestResponse<>(restTemplate
                .exchange(uri, toHttpMethod(method), entity, GenericParameterizedTypeReference.of(responseType)));
        }
        catch (RestClientResponseException e)
        {
            throw new RestResponseException(toDescription(method, uri, e), e.getRawStatusCode(), e.getStatusText(), e);
        }
        catch (Exception e)
        {
            throw new RestException(method + " " + uri, e);
        }
    }

    protected HttpMethod toHttpMethod(RestMethod method)
    {
        switch (method)
        {
            case DELETE:
                return HttpMethod.DELETE;

            case GET:
                return HttpMethod.GET;

            case OPTIONS:
                return HttpMethod.OPTIONS;

            case PATCH:
                return HttpMethod.PATCH;

            case POST:
                return HttpMethod.POST;

            case PUT:
                return HttpMethod.PUT;

            default:
                throw new UnsupportedOperationException("Unsupported method: " + method);
        }
    }

    protected URI processAttributes(HttpHeaders headers, boolean form) throws RestException
    {
        List<MediaType> acceptableMediaTypes = getAcceptableMediaTypes();

        if (acceptableMediaTypes != null)
        {
            headers.setAccept(convertMediaTypes(acceptableMediaTypes));
        }

        MediaType contentType = getContentType();

        if (contentType != null)
        {
            headers.setContentType(SpringRestUtils.convertMediaType(contentType, null));
        }

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(getUrl());

        factory.setEncodingMode(EncodingMode.URI_COMPONENT);

        UriBuilder builder = factory.builder();
        Map<String, Object> variables = buildVariables(builder, headers, form);

        try
        {
            return builder.build(variables).toURL().toURI();
        }
        catch (MalformedURLException | URISyntaxException e)
        {
            throw new IllegalArgumentException("Failed to parse URL", e);
        }
    }

    private Map<String, Object> buildVariables(UriBuilder builder, HttpHeaders headers, boolean form)
        throws RestException
    {
        Map<String, Object> variables = new HashMap<>();
        List<RestAttribute> attributes = getAttributes();

        if (attributes == null)
        {
            return variables;
        }

        int id = 0;

        for (RestAttribute attribute : attributes)
        {
            Object value = attribute.getValue();

            if (value == null)
            {
                continue;
            }

            String name = attribute.getName();

            if (attribute instanceof RestHeader)
            {
                headers.add(name, format(MediaType.TEXT_PLAIN, value));

                continue;
            }

            if (attribute instanceof RestParameter)
            {
                if (!form)
                {
                    id = appendRestParameter(builder, variables, id, value, name);
                }

                continue;
            }

            if (attribute instanceof RestVariable)
            {
                variables.put(name, format(MediaType.TEXT_PLAIN, value));

                continue;
            }

            throw new RestException("Rest attrbiute of %s not supported", attribute.getClass());
        }

        return variables;
    }

    private int appendRestParameter(UriBuilder builder, Map<String, Object> variables, int id, Object value,
        String name)
    {
        if (value.getClass().isArray())
        {
            for (int i = 0; i < Array.getLength(value); i++)
            {
                queryParam(builder, variables, name, id++, format(MediaType.TEXT_PLAIN, Array.get(value, i)));
            }
        }
        else if (value instanceof Iterable<?>)
        {
            Iterator<?> iterator = ((Iterable<?>) value).iterator();

            while (iterator.hasNext())
            {
                queryParam(builder, variables, name, id++, format(MediaType.TEXT_PLAIN, iterator.next()));
            }
        }
        else
        {
            queryParam(builder, variables, name, id++, format(MediaType.TEXT_PLAIN, value));
        }

        return id;
    }

    private void queryParam(UriBuilder builder, Map<String, Object> variables, String name, int id, Object value)
    {
        String key = "#" + id;

        builder.queryParam(name, "{" + key + "}");
        variables.put(key, value);
    }

    protected static String toDescription(RestMethod method, URI uri, RestClientResponseException e)
    {
        return method + " " + uri + ": " + e.getResponseBodyAsString();
    }
}
