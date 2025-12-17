package at.porscheinformatik.happyrest.spring;

import static at.porscheinformatik.happyrest.spring.SpringRestUtils.*;

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
import at.porscheinformatik.happyrest.RestRequestException;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestResponseException;
import at.porscheinformatik.happyrest.RestVariable;
import java.lang.reflect.Array;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.HashMap;
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
import pnet.data.api.ErrorResult;

/**
 * A REST call. This implementation is thread-safe!
 *
 * @author ham
 */
public class RestTemplateRestCall extends AbstractRestCall {

    protected final RestTemplate restTemplate;

    protected RestTemplateRestCall(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter
    ) {
        this(restTemplate, loggerAdapter, null, null, MediaType.APPLICATION_JSON, null, formatter, null);
    }

    protected RestTemplateRestCall(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter,
        String url
    ) {
        this(restTemplate, loggerAdapter, url, null, MediaType.APPLICATION_JSON, null, formatter, null);
    }

    @SuppressWarnings("java:S107") // As usual, the high number of parameters is necessary
    protected RestTemplateRestCall(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        Object body
    ) {
        super(loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter, body);
        this.restTemplate = restTemplate;
    }

    @Override
    protected RestCall copy(
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        Object body
    ) {
        return new RestTemplateRestCall(
            restTemplate,
            loggerAdapter,
            url,
            acceptableMediaTypes,
            contentType,
            attributes,
            formatter,
            body
        );
    }

    @Override
    public Object getBody() {
        Object body = super.getBody();

        if (body != null) {
            return body;
        }

        if (isForm()) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

            getParameters().forEach(attribute ->
                map.add(attribute.getName(), format(MediaType.TEXT_PLAIN, attribute.getValue()))
            );

            return map;
        }

        return body;
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException {
        boolean form = verify(method);

        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, form);
        HttpEntity<Object> entity = new HttpEntity<>(getBody(), headers);

        getLoggerAdapter().logRequest(method, String.valueOf(uri));

        return invoke(method, responseType, uri, entity);
    }

    protected <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType, URI uri, HttpEntity<Object> entity)
        throws RestException {
        try {
            return new SpringRestResponse<>(restTemplate.exchange(uri, toHttpMethod(method), entity, responseType));
        } catch (RestClientResponseException e) {
            throw new RestResponseException(
                toErrorResult(e, method, uri),
                e.getStatusCode().value(),
                e.getStatusText(),
                e
            );
        } catch (Exception e) {
            throw new RestException(method + " " + uri, e);
        }
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException {
        if (responseType.getArguments().length == 0) {
            // If the rest template is called with a GenericParameterizedTypeReference,
            // it will always assume, that the response is application/json. This
            // breaks stuff, if it should parse a String from a text/plain response.
            return invoke(method, responseType.getType());
        }

        boolean form = verify(method);

        HttpHeaders headers = new HttpHeaders();
        URI uri = processAttributes(headers, form);
        HttpEntity<Object> entity = new HttpEntity<>(getBody(), headers);

        getLoggerAdapter().logRequest(method, String.valueOf(uri));

        return invoke(method, responseType, uri, entity);
    }

    protected <T> RestResponse<T> invoke(
        RestMethod method,
        GenericType<T> responseType,
        URI uri,
        HttpEntity<Object> entity
    ) throws RestException {
        try {
            return new SpringRestResponse<>(
                restTemplate.exchange(
                    uri,
                    toHttpMethod(method),
                    entity,
                    GenericParameterizedTypeReference.of(responseType)
                )
            );
        } catch (RestClientResponseException e) {
            throw new RestResponseException(
                toErrorResult(e, method, uri),
                e.getStatusCode().value(),
                e.getStatusText(),
                e
            );
        } catch (Exception e) {
            throw new RestException(method + " " + uri, e);
        }
    }

    protected HttpMethod toHttpMethod(RestMethod method) {
        return switch (method) {
            case DELETE -> HttpMethod.DELETE;
            case GET -> HttpMethod.GET;
            case OPTIONS -> HttpMethod.OPTIONS;
            case PATCH -> HttpMethod.PATCH;
            case POST -> HttpMethod.POST;
            case PUT -> HttpMethod.PUT;
            default -> throw new UnsupportedOperationException("Unsupported method: " + method);
        };
    }

    protected URI processAttributes(HttpHeaders headers, boolean form) throws RestException {
        List<MediaType> acceptableMediaTypes = getAcceptableMediaTypes();

        if (acceptableMediaTypes != null) {
            headers.setAccept(convertMediaTypes(acceptableMediaTypes));
        }

        MediaType contentType = getContentType();

        if (contentType != null) {
            headers.setContentType(SpringRestUtils.convertMediaType(contentType, null));
        }

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(getUrl());

        factory.setEncodingMode(EncodingMode.URI_COMPONENT);

        UriBuilder builder = factory.builder();
        Map<String, Object> variables = buildVariables(builder, headers, form);

        try {
            return builder.build(variables).toURL().toURI();
        } catch (Exception e) {
            throw new RestRequestException("Failed to build URL", e);
        }
    }

    @SuppressWarnings("java:S135") // Multiple continues are ok
    private Map<String, Object> buildVariables(UriBuilder builder, HttpHeaders headers, boolean form)
        throws RestException {
        Map<String, Object> variables = new HashMap<>();
        List<RestAttribute> attributes = getAttributes();

        if (attributes == null) {
            return variables;
        }

        int id = 0;

        for (RestAttribute attribute : attributes) {
            Object value = attribute.getValue();

            if (value == null) {
                continue;
            }

            String name = attribute.getName();

            if (attribute instanceof RestHeader) {
                headers.add(name, format(MediaType.TEXT_PLAIN, value));

                continue;
            }

            if (attribute instanceof RestParameter) {
                if (!form) {
                    id = appendRestParameter(builder, variables, id, value, name);
                }

                continue;
            }

            if (attribute instanceof RestVariable) {
                variables.put(name, format(MediaType.TEXT_PLAIN, value));

                continue;
            }

            throw new RestException("Rest attrbiute of " + attribute.getClass() + " not supported");
        }

        return variables;
    }

    private int appendRestParameter(
        UriBuilder builder,
        Map<String, Object> variables,
        int id,
        Object value,
        String name
    ) {
        if (value.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(value); i++) {
                queryParam(builder, variables, name, id++, format(MediaType.TEXT_PLAIN, Array.get(value, i)));
            }
        } else if (value instanceof Iterable<?>) {
            for (Object element : ((Iterable<?>) value)) {
                queryParam(builder, variables, name, id++, format(MediaType.TEXT_PLAIN, element));
            }
        } else {
            queryParam(builder, variables, name, id++, format(MediaType.TEXT_PLAIN, value));
        }

        return id;
    }

    private void queryParam(UriBuilder builder, Map<String, Object> variables, String name, int id, Object value) {
        String key = "#" + id;

        builder.queryParam(name, "{" + key + "}");
        variables.put(key, value);
    }

    protected ErrorResult toErrorResult(RestClientResponseException exception, RestMethod method, URI uri) {
        try {
            var result = exception.getResponseBodyAs(ErrorResult.class);

            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            // ignore
        }

        return new ErrorResult(
            exception.getStatusCode().value() + " " + exception.getStatusText(),
            null,
            exception.getMessage(),
            exception.getResponseBodyAsString(),
            method + " " + uri,
            ZonedDateTime.now()
        );
    }
}
