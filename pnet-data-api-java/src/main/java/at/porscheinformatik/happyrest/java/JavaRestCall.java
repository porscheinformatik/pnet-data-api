package at.porscheinformatik.happyrest.java;

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
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestRequestException;
import at.porscheinformatik.happyrest.RestResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A REST call. This implementation is thread-safe!
 *
 * @author HAM
 */
public class JavaRestCall extends AbstractRestCall {

    protected final HttpClient httpClient;
    protected final String userAgent;
    protected final RestParser parser;

    public JavaRestCall(
        HttpClient httpClient,
        String userAgent,
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
        this.userAgent = userAgent;
        this.parser = parser;
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
        return new JavaRestCall(
            httpClient,
            userAgent,
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
        String url = buildUrl(form);
        HttpRequest request = buildRequest(method, url, form);

        getLoggerAdapter().logRequest(method, String.valueOf(request.uri()));

        return invoke(request, responseType);
    }

    protected <T> RestResponse<T> invoke(HttpRequest request, GenericType<T> responseType)
        throws RestRequestException, RestException {
        HttpResponse<InputStream> response;

        try {
            response = httpClient.send(request, BodyHandlers.ofInputStream());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new RestRequestException("Request got interrupted: " + String.valueOf(request.uri()), e);
        } catch (IOException e) {
            throw new RestRequestException("Request failed: " + String.valueOf(request.uri()), e);
        }

        return JavaRestResponse.create(parser, response, responseType, getLoggerAdapter());
    }

    private HttpRequest buildRequest(RestMethod method, String url, boolean form) throws RestRequestException {
        Builder builder;

        try {
            builder = HttpRequest.newBuilder().uri(URI.create(url));
        } catch (Exception e) {
            throw new RestRequestException("Failed to build URL: " + url, e);
        }

        List<RestHeader> headers = getHeaders();

        for (RestHeader header : headers) {
            builder.header(header.getName(), format(MediaType.TEXT_PLAIN, header.getValue()));
        }

        List<MediaType> acceptableMediaTypes = getAcceptableMediaTypes();

        if (acceptableMediaTypes != null) {
            builder.header("Accept", MediaType.format(acceptableMediaTypes));
        }

        switch (method) {
            case GET:
                builder = builder.GET();
                break;
            case POST:
                builder = builder.POST(createBodyPublisher());
                break;
            case PUT:
                builder = builder.PUT(createBodyPublisher());
                break;
            case DELETE:
                builder = builder.DELETE();
                break;
            default:
                throw new UnsupportedOperationException("Unsupported method: " + method);
        }

        if (userAgent != null) {
            builder.setHeader("User-Agent", userAgent);
        }

        MediaType contentType = getContentType();

        if (contentType != null) {
            builder.setHeader("Content-Type", contentType.toString());
        }

        return builder.build();
    }

    protected BodyPublisher createBodyPublisher() {
        Object body = getBody();

        if (body != null) {
            return BodyPublishers.ofString(format(getContentType(), body));
        }

        if (isForm()) {
            List<String> parameters = collectParameters();

            if (!parameters.isEmpty()) {
                return BodyPublishers.ofString(parameters.stream().collect(Collectors.joining("&")));
            }
        }

        return BodyPublishers.noBody();
    }
}
