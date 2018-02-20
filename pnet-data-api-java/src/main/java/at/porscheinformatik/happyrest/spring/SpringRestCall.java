package at.porscheinformatik.happyrest.spring;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import at.porscheinformatik.happyrest.AbstractRestCall;
import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestHeader;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestParameter;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestVariable;

/**
 * A REST call. This implementation is thread-safe!
 *
 * @author ham
 */
public class SpringRestCall extends AbstractRestCall
{

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(SpringRestCall.class);

    private final RestTemplate restTemplate;

    public SpringRestCall()
    {
        this(REST_TEMPLATE, null, null, null, null);
    }

    public SpringRestCall(String url)
    {
        this(REST_TEMPLATE, url, null, null, null);
    }

    public SpringRestCall(RestTemplate restTemplate)
    {
        this(restTemplate, null, null, null, null);
    }

    public SpringRestCall(RestTemplate restTemplate, String url)
    {
        this(restTemplate, url, null, null, null);
    }

    protected SpringRestCall(RestTemplate restTemplate, String url, List<String> acceptableMediaTypes,
        List<RestAttribute> attributes, Object body)
    {
        super(url, acceptableMediaTypes, attributes, body);

        this.restTemplate = restTemplate;
    }

    @Override
    protected RestCall copy(String url, List<String> acceptableMediaTypes, List<RestAttribute> attributes, Object body)
    {
        return new SpringRestCall(restTemplate, url, acceptableMediaTypes, attributes, body);
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType)
    {
        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, path);
        HttpEntity<Object> entity = new HttpEntity<>(getBody(), headers);

        LOG.info(String.format("Sending %s request: %s", method, uri));

        try
        {
            return new SpringRestResponse<>(restTemplate.exchange(uri, toHttpMethod(method), entity, responseType));
        }
        catch (HttpClientErrorException e)
        {
            LOG.warn(String.format("%s Request \"%s\" failed: %s %s\n\tHeaders: %s\n\tBody: %s", method, uri,
                e.getStatusCode(), e.getStatusText(), e.getResponseHeaders(), e.getResponseBodyAsString()));

            throw e;
        }
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType)
    {
        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, path);
        HttpEntity<Object> entity = new HttpEntity<>(getBody(), headers);

        LOG.info(String.format("Sending %s request: %s", method, uri));

        try
        {
            return new SpringRestResponse<>(restTemplate.exchange(uri, toHttpMethod(method), entity,
                GenericParameterizedTypeReference.of(responseType)));
        }
        catch (HttpStatusCodeException e)
        {
            LOG.warn(String.format("%s Request \"%s\" failed: %s %s\n\tHeaders: %s\n\tBody: %s", method, uri,
                e.getStatusCode(), e.getStatusText(), e.getResponseHeaders(), e.getResponseBodyAsString()));

            throw e;
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

    protected URI processAttributes(HttpHeaders headers, String path)
    {
        List<String> acceptableMediaTypes = getAcceptableMediaTypes();

        if (acceptableMediaTypes != null)
        {
            headers.setAccept(MediaType.parseMediaTypes(acceptableMediaTypes));
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(prepareUrl(getUrl(), path));
        Map<String, Object> variables = new HashMap<>();
        List<RestAttribute> attributes = getAttributes();

        if (attributes != null)
        {
            for (RestAttribute attribute : attributes)
            {
                Object value = attribute.getValue();
                String name = attribute.getName();

                if (attribute instanceof RestHeader)
                {
                    headers.add(name, (String) attribute.getValue());

                    continue;
                }

                if (value == null)
                {
                    continue;
                }

                if (attribute instanceof RestParameter)
                {
                    if (value instanceof Object[])
                    {
                        Arrays.stream((Object[]) value).forEach(v -> builder.queryParam(name, v));
                    }
                    else if (value instanceof double[])
                    {
                        Arrays.stream((double[]) value).forEach(v -> builder.queryParam(name, v));
                    }
                    else if (value instanceof long[])
                    {
                        Arrays.stream((long[]) value).forEach(v -> builder.queryParam(name, v));
                    }
                    else if (value instanceof int[])
                    {
                        Arrays.stream((int[]) value).forEach(v -> builder.queryParam(name, v));
                    }
                    else if (value instanceof Iterable<?>)
                    {
                        ((Iterable<?>) value).forEach(v -> builder.queryParam(name, v));
                    }
                    else
                    {
                        builder.queryParam(name, value);
                    }

                    continue;
                }

                if (attribute instanceof RestVariable)
                {
                    variables.put(name, value);

                    continue;
                }

                throw new IllegalArgumentException("Rest attrbiute of " + attribute.getClass() + " not supported.");
            }
        }

        return builder.buildAndExpand(variables).encode().toUri();
    }

}
