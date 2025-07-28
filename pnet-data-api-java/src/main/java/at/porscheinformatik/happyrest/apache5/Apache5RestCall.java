package at.porscheinformatik.happyrest.apache5;

import at.porscheinformatik.happyrest.AbstractRestCall;
import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestException;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestParameter;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestRequestException;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestUtils;
import at.porscheinformatik.happyrest.RestVariable;
import at.porscheinformatik.happyrest.RuntimeRestExceptionWrapper;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpOptions;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

/**
 * A REST call. This implementation is thread-safe!
 *
 * @author HAM
 */
public class Apache5RestCall extends AbstractRestCall {

    protected final CloseableHttpClient httpClient;
    protected final RestParser parser;

    @SuppressWarnings("java:S107") // Too many parameters
    public Apache5RestCall(
        CloseableHttpClient httpClient,
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        RestParser parser,
        Object body
    ) {
        super(loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter, body);
        this.httpClient = httpClient;
        this.parser = parser;
    }

    protected HttpEntity createEntity(MediaType contentType) {
        Object body = getBody();

        if (body != null) {
            return new StringEntity(format(contentType, body), Apache5RestUtils.convertMediaType(contentType, null));
        }

        if (isForm()) {
            List<NameValuePair> params = new ArrayList<>();

            getParameters().forEach(parameter ->
                params.add(
                    new BasicNameValuePair(parameter.getName(), format(MediaType.TEXT_PLAIN, parameter.getValue()))
                )
            );

            return new UrlEncodedFormEntity(params, getCharset());
        }

        return null;
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
        return new Apache5RestCall(
            httpClient,
            loggerAdapter,
            url,
            acceptableMediaTypes,
            contentType,
            attributes,
            formatter,
            parser,
            body
        );
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException {
        return invoke(method, GenericType.build(responseType).raw());
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException {
        boolean form = verify(method);
        String url = buildUrl();

        HttpUriRequestBase request = buildRequest(method, url, form);

        computeHeaders(request);
        computeEntity(method, request);

        getLoggerAdapter().logRequest(method, request.getRequestUri());

        return invoke(method, responseType, request);
    }

    protected <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType, HttpUriRequestBase request)
        throws RestException {
        try {
            return httpClient.<RestResponse<T>>execute(request, response -> {
                try {
                    return Apache5RestResponse.create(parser, response, responseType);
                } catch (RestException e) {
                    throw new RuntimeRestExceptionWrapper(e);
                }
            });
        } catch (RuntimeRestExceptionWrapper e) {
            throw e.getCause();
        } catch (IOException e) {
            throw new RestRequestException("Request failed", e);
        }
    }

    private String buildUrl() {
        String url = getUrl();
        Charset charset = getCharset();

        for (RestVariable variable : getVariables()) {
            url = url.replace(
                "{" + variable.getName() + "}",
                RestUtils.encodePathSegment(format(MediaType.TEXT_PLAIN, variable.getValue()), charset, false, false)
            );
        }

        return url;
    }

    private HttpUriRequestBase buildRequest(RestMethod method, String url, boolean form) throws RestRequestException {
        HttpUriRequestBase request;

        try {
            URIBuilder builder = new URIBuilder(url);

            if (!form) {
                populateWithParameters(builder);
            }

            request = switch (method) {
                case GET -> new HttpGet(builder.build());
                case POST -> new HttpPost(builder.build());
                case PUT -> new HttpPut(builder.build());
                case DELETE -> new HttpDelete(builder.build());
                case OPTIONS -> new HttpOptions(builder.build());
                case PATCH -> new HttpPatch(builder.build());
                default -> throw new UnsupportedOperationException("Unsupported method: " + method);
            };
        } catch (URISyntaxException e) {
            throw new RestRequestException("Invalid URL: " + url, e);
        }
        return request;
    }

    private void populateWithParameters(URIBuilder builder) {
        for (RestParameter parameter : getParameters()) {
            Object value = parameter.getValue();

            if (value == null) {
                continue;
            }

            if (value.getClass().isArray()) {
                for (int i = 0; i < Array.getLength(value); i++) {
                    builder.addParameter(parameter.getName(), format(MediaType.TEXT_PLAIN, Array.get(value, i)));
                }

                continue;
            }

            if (value instanceof Iterable<?>) {
                for (Object element : ((Iterable<?>) value)) {
                    builder.addParameter(parameter.getName(), format(MediaType.TEXT_PLAIN, element));
                }

                continue;
            }

            builder.addParameter(parameter.getName(), format(MediaType.TEXT_PLAIN, value));
        }
    }

    private void computeHeaders(HttpUriRequestBase request) {
        getHeaders().forEach(header ->
            request.addHeader(header.getName(), format(MediaType.TEXT_PLAIN, header.getValue()))
        );

        MediaType contentType = getContentType();

        if (contentType != null) {
            request.addHeader("Content-Type", contentType.toString());
        }

        List<MediaType> acceptableMediaTypes = getAcceptableMediaTypes();

        if (acceptableMediaTypes != null) {
            request.addHeader("Accept", MediaType.format(acceptableMediaTypes));
        }
    }

    private void computeEntity(RestMethod method, HttpUriRequestBase request) {
        HttpEntity requestEntity = createEntity(getContentType());

        if (requestEntity != null) {
            request.setEntity(requestEntity);
        }
    }
}
