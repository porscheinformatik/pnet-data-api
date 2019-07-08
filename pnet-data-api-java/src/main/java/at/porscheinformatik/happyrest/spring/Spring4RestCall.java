package at.porscheinformatik.happyrest.spring;

import java.lang.reflect.Array;
import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

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
 * A REST call, compatible to Spring4. This implementation is thread-safe!
 *
 * @author ham
 */
public class Spring4RestCall extends AbstractRestCall
{
    private final RestTemplate restTemplate;
    private final RestLoggerAdapter loggerAdapter;

    protected Spring4RestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter,
        ConversionService conversionService)
    {
        this(restTemplate, loggerAdapter, MediaType.APPLICATION_JSON_VALUE, null, null, null,
            toConverter(conversionService), null);
    }

    protected Spring4RestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter,
        ConversionService conversionService, String url)
    {
        this(restTemplate, loggerAdapter, url, null, MediaType.APPLICATION_JSON_VALUE, null,
            toConverter(conversionService), (Object) null);
    }

    protected Spring4RestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, String url,
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
        return new Spring4RestCall(restTemplate, loggerAdapter, url, acceptableMediaTypes, contentType, attributes,
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
            return new Spring4RestResponse<>(restTemplate.exchange(uri, toHttpMethod(method), entity, responseType));
        }
        catch (RestClientResponseException e)
        {
            throw new RestResponseException(toDescription(method, uri, e), e.getRawStatusCode(), e.getStatusText(), e);
        }
        catch (Exception var9)
        {
            throw new RestException(method + " " + uri, var9, new Object[0]);
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
            return new Spring4RestResponse<>(restTemplate
                .exchange(uri, toHttpMethod(method), entity, GenericParameterizedTypeReference.of(responseType)));
        }
        catch (RestClientResponseException e)
        {
            throw new RestResponseException(toDescription(method, uri, e), e.getRawStatusCode(), e.getStatusText(), e);
        }
        catch (Exception var9)
        {
            throw new RestException(method + " " + uri, var9, new Object[0]);
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
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(prepareUrl(getUrl(), path));

        Map<String, Object> variables = new HashMap<>();
        List<RestAttribute> attributes = this.getAttributes();
        int id = 0;
        if (attributes != null)
        {
            Iterator<RestAttribute> var10 = attributes.iterator();

            label65: while (true)
            {
                while (true)
                {
                    RestAttribute attribute;
                    Object value;
                    do
                    {
                        if (!var10.hasNext())
                        {
                            break label65;
                        }

                        attribute = var10.next();
                        value = attribute.getValue();
                    } while (value == null);

                    String name = attribute.getName();
                    if (attribute instanceof RestHeader)
                    {
                        headers.add(name, convert(value));
                    }
                    else if (attribute instanceof RestParameter)
                    {
                        if (value.getClass().isArray())
                        {
                            for (int i = 0; i < Array.getLength(value); ++i)
                            {
                                queryParam(builder, variables, name, id++, convert(Array.get(value, i)));
                            }
                        }
                        else if (value instanceof Iterable)
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
                    }
                    else
                    {
                        if (!(attribute instanceof RestVariable))
                        {
                            throw new RestException("Rest attrbiute of %s not supported",
                                new Object[]{attribute.getClass()});
                        }

                        variables.put(name, convert(value));
                    }
                }
            }
        }

        try
        {
            return builder.buildAndExpand(variables).toUri();
        }
        catch (RuntimeException ex)
        {
            throw new IllegalArgumentException("Failed to parse URL", ex);
        }
    }

    private void queryParam(UriComponentsBuilder builder, Map<String, Object> variables, String name, int id,
        Object value)
    {
        String key = "#" + id;
        builder.queryParam(name, new Object[]{"{" + key + "}"});
        variables.put(key, value);
    }

    protected static RestAttributeConverter toConverter(ConversionService conversionService)
    {
        return conversionService != null ? (value) -> {
            return conversionService.convert(value, String.class);
        } : (value) -> {
            return String.valueOf(value);
        };
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
