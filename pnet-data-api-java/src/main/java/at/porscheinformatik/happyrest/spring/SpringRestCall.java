package at.porscheinformatik.happyrest.spring;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode;
import org.springframework.web.util.UriBuilder;

import at.porscheinformatik.happyrest.AbstractRestCall;
import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestAttributeConverter;
import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestException;
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

    protected SpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter,
        ConversionService conversionService)
    {
        this(restTemplate, loggerAdapter, null, null, MediaType.APPLICATION_JSON_UTF8_VALUE, null,
            toConverter(conversionService), null);
    }

    protected SpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter,
        ConversionService conversionService, String url)
    {
        this(restTemplate, loggerAdapter, url, null, MediaType.APPLICATION_JSON_UTF8_VALUE, null,
            toConverter(conversionService), null);
    }

    protected SpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, String url,
        List<String> acceptableMediaTypes, String contentType, List<RestAttribute> attributes,
        RestAttributeConverter converter, Object body)
    {
        super(url, acceptableMediaTypes, contentType, attributes, converter, body);

        this.restTemplate = restTemplate;
        this.loggerAdapter = loggerAdapter;
    }

    @Override
    protected RestCall copy(String url, List<String> acceptableMediaTypes, String contentType,
        List<RestAttribute> attributes, RestAttributeConverter converter, Object body)
    {
        return new SpringRestCall(restTemplate, loggerAdapter, url, acceptableMediaTypes, contentType, attributes,
            converter, body);
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType) throws RestException
    {
        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, path);
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
    public <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType) throws RestException
    {
        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, path);
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

    protected URI processAttributes(HttpHeaders headers, String path) throws RestException
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

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(prepareUrl(getUrl(), path));

        factory.setEncodingMode(EncodingMode.URI_COMPONENT);

        UriBuilder builder = factory.builder();
        Map<String, Object> variables = new HashMap<>();
        List<RestAttribute> attributes = getAttributes();
        int id = 0;

        if (attributes != null)
        {
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
                    headers.add(name, convert(value));

                    continue;
                }

                if (attribute instanceof RestParameter)
                {
                    if (value.getClass().isArray())
                    {
                        for (int i = 0; i < Array.getLength(value); i++)
                        {
                            queryParam(builder, variables, name, id++, convert(Array.get(value, i)));
                        }
                    }
                    else if (value instanceof Iterable<?>)
                    {
                        Iterator<?> iterator = ((Iterable<?>) value).iterator();

                        while (iterator.hasNext())
                        {
                            queryParam(builder, variables, name, id++, convert(iterator.next()));
                        }
                    }
                    else
                    {
                        queryParam(builder, variables, name, id++, convert(value));
                    }

                    continue;
                }

                if (attribute instanceof RestVariable)
                {
                    variables.put(name, convert(value));

                    continue;
                }

                throw new RestException("Rest attrbiute of %s not supported", attribute.getClass());
            }
        }

        try
        {
            return builder.build(variables).toURL().toURI();
        }
        catch (MalformedURLException | URISyntaxException e)
        {
            throw new IllegalArgumentException("Failed to parse URL", e);
        }
    }

    private void queryParam(UriBuilder builder, Map<String, Object> variables, String name, int id, Object value)
    {
        String key = "#" + id;

        builder.queryParam(name, "{" + key + "}");
        variables.put(key, value);
    }

    protected static RestAttributeConverter toConverter(ConversionService conversionService)
    {
        return conversionService != null ? value -> conversionService.convert(value, String.class)
            : value -> String.valueOf(value);
    }

    protected static String toDescription(RestMethod method, URI uri, RestClientResponseException e)
    {
        String description = method + " " + uri;
        String body = e.getResponseBodyAsString();

        if (body != null)
        {
            description += ": " + body;
        }

        return description;
    }

}
