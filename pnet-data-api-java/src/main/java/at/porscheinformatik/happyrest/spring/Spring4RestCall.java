package at.porscheinformatik.happyrest.spring;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import at.porscheinformatik.happyrest.AbstractRestCall;
import at.porscheinformatik.happyrest.GenericType;
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
import at.porscheinformatik.happyrest.RestUtils;
import at.porscheinformatik.happyrest.RestVariable;

/**
 * A REST call, compatible to Spring4. This implementation is thread-safe!
 *
 * @author ham
 */
public class Spring4RestCall extends AbstractRestCall
{
    private final RestTemplate restTemplate;
    private final RestLoggerAdapter loggerAdapter;

    protected Spring4RestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter)
    {
        this(restTemplate, loggerAdapter, null, null, MediaType.APPLICATION_JSON_VALUE, null, formatter, null);
    }

    protected Spring4RestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter,
        String url)
    {
        this(restTemplate, loggerAdapter, url, null, MediaType.APPLICATION_JSON_VALUE, null, formatter, (Object) null);
    }

    protected Spring4RestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, String url,
        List<String> acceptableMediaTypes, String contentType, List<RestAttribute> attributes, RestFormatter formatter,
        Object body)
    {
        super(url, acceptableMediaTypes, contentType, attributes, formatter, body);

        this.restTemplate = restTemplate;
        this.loggerAdapter = loggerAdapter;
    }

    @Override
    protected RestCall copy(String url, List<String> acceptableMediaTypes, String contentType,
        List<RestAttribute> attributes, RestFormatter formatter, Object body)
    {
        return new Spring4RestCall(restTemplate, loggerAdapter, url, acceptableMediaTypes, contentType, attributes,
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
                .forEach(
                    attribute -> map.add(attribute.getName(), format(MEDIA_TYPE_TEXT_PLAIN, attribute.getValue())));

            return map;
        }

        return body;
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType) throws RestException
    {
        boolean form = verify(method);

        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, path, form);
        HttpEntity<Object> entity = new HttpEntity<>(getBody(), headers);

        loggerAdapter.logRequest(method, String.valueOf(uri));

        try
        {
            return new Spring4RestResponse<>(restTemplate.exchange(uri, toHttpMethod(method), entity, responseType));
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
    public <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType) throws RestException
    {
        boolean form = verify(method);

        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, path, form);
        HttpEntity<Object> entity = new HttpEntity<>(getBody(), headers);

        loggerAdapter.logRequest(method, String.valueOf(uri));

        try
        {
            return new Spring4RestResponse<>(restTemplate
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

    protected URI processAttributes(HttpHeaders headers, String path, boolean form) throws RestException
    {
        List<String> acceptableMediaTypes = getAcceptableMediaTypes();

        if (acceptableMediaTypes != null)
        {
            headers.setAccept(MediaType.parseMediaTypes(acceptableMediaTypes));
        }

        String contentType = getContentType();

        if (contentType != null)
        {
            headers.setContentType(MediaType.parseMediaType(contentType));
        }

        UriComponentsBuilder builder =
            UriComponentsBuilder.fromUriString(RestUtils.appendPathWithPlaceholders(getUrl(), path));
        Map<String, Object> variables = buildVariables(builder, headers, form);

        try
        {
            return builder.buildAndExpand(variables).toUri();
        }
        catch (RuntimeException e)
        {
            throw new IllegalArgumentException("Failed to parse URL", e);
        }
    }

    private Map<String, Object> buildVariables(UriComponentsBuilder builder, HttpHeaders headers, boolean form)
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
                headers.add(name, format(MEDIA_TYPE_TEXT_PLAIN, value));

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
                variables.put(name, format(MEDIA_TYPE_TEXT_PLAIN, value));

                continue;
            }

            throw new RestException("Rest attrbiute of %s not supported", attribute.getClass());
        }

        return variables;
    }

    private int appendRestParameter(UriComponentsBuilder builder, Map<String, Object> variables, int id, Object value,
        String name)
    {
        if (value.getClass().isArray())
        {
            for (int i = 0; i < Array.getLength(value); i++)
            {
                queryParam(builder, variables, name, id++, format(MEDIA_TYPE_TEXT_PLAIN, Array.get(value, i)));
            }
        }
        else if (value instanceof Iterable<?>)
        {
            Iterator<?> iterator = ((Iterable<?>) value).iterator();

            while (iterator.hasNext())
            {
                queryParam(builder, variables, name, id++, format(MEDIA_TYPE_TEXT_PLAIN, iterator.next()));
            }
        }
        else
        {
            queryParam(builder, variables, name, id++, format(MEDIA_TYPE_TEXT_PLAIN, value));
        }

        return id;
    }

    private void queryParam(UriComponentsBuilder builder, Map<String, Object> variables, String name, int id,
        Object value)
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
