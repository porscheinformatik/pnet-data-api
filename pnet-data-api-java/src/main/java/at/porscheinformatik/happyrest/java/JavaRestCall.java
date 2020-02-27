package at.porscheinformatik.happyrest.java;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestRequestException;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestVariable;

/**
 * A REST call. This implementation is thread-safe!
 *
 * @author HAM
 */
public class JavaRestCall extends AbstractRestCall
{

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private final RestLoggerAdapter loggerAdapter;
    private final RestParser parser;

    public JavaRestCall(RestLoggerAdapter loggerAdapter, String url, List<String> acceptableMediaTypes,
        String contentType, List<RestAttribute> attributes, RestFormatter formatter, RestParser parser, Object body)
    {
        super(url, acceptableMediaTypes, contentType, attributes, formatter, body);

        this.loggerAdapter = loggerAdapter;
        this.parser = parser;
    }

    @Override
    protected RestCall copy(String url, List<String> acceptableMediaTypes, String contentType,
        List<RestAttribute> attributes, RestFormatter formatter, Object body)
    {
        return new JavaRestCall(loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter, parser,
            body);
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType) throws RestException
    {
        return invoke(method, path, GenericType.build(responseType).raw());
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType) throws RestException
    {
        boolean form = verify(method);
        String url = buildUrl(path, form);
        HttpRequest request = buildRequest(method, url, form);

        loggerAdapter.logRequest(method, request.toString());

        HttpResponse<InputStream> response;
        try
        {
            response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofInputStream());
        }
        catch (IOException | InterruptedException e)
        {
            throw new RestRequestException("Request failed: %s", url, e);
        }

        return JavaRestResponse.create(parser, response, responseType, loggerAdapter);
    }

    private String buildUrl(String path, boolean form)
    {
        String url = getUrl();

        if (path != null)
        {
            if (path.startsWith("/"))
            {
                path = path.substring(1);
            }

            if (url.endsWith("/"))
            {
                url += path;
            }
            else
            {
                url += "/" + path;
            }
        }

        for (RestVariable variable : getVariables())
        {
            url = url.replace("{" + variable.getName() + "}", format(MEDIA_TYPE_TEXT_PLAIN, variable.getValue()));
        }

        if (!form)
        {
            List<String> parameters = collectParameters();

            if (!parameters.isEmpty())
            {
                url += "?" + parameters.stream().collect(Collectors.joining("&"));
            }
        }

        return url;
    }

    private HttpRequest buildRequest(RestMethod method, String url, boolean form) throws RestRequestException
    {
        Builder builder = HttpRequest.newBuilder().uri(URI.create(url));
        List<RestHeader> headers = getHeaders();

        for (RestHeader header : headers)
        {
            builder.header(header.getName(), format(MEDIA_TYPE_TEXT_PLAIN, header.getValue()));
        }

        switch (method)
        {
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

        return builder.build();

    }

    protected BodyPublisher createBodyPublisher()
    {
        Object body = getBody();

        if (body != null)
        {
            return BodyPublishers.ofString(format(getContentType(), body));
        }

        if (isForm())
        {
            List<String> parameters = collectParameters();

            if (!parameters.isEmpty())
            {
                return BodyPublishers.ofString(parameters.stream().collect(Collectors.joining("&")));
            }
        }

        return BodyPublishers.noBody();
    }

    protected List<String> collectParameters()
    {
        List<String> parameters = new ArrayList<>();
        Charset charset = getCharset();

        for (RestParameter parameter : getParameters())
        {
            Object value = parameter.getValue();

            if (value == null)
            {
                continue;
            }

            if (value.getClass().isArray())
            {
                for (int i = 0; i < Array.getLength(value); i++)
                {
                    parameters
                        .add(URLEncoder.encode(parameter.getName(), charset)
                            + "="
                            + URLEncoder.encode(format(MEDIA_TYPE_TEXT_PLAIN, Array.get(value, i)), charset));
                }

                continue;
            }

            if (value instanceof Iterable<?>)
            {
                Iterator<?> iterator = ((Iterable<?>) value).iterator();

                while (iterator.hasNext())
                {
                    parameters
                        .add(URLEncoder.encode(parameter.getName(), charset)
                            + "="
                            + URLEncoder.encode(format(MEDIA_TYPE_TEXT_PLAIN, iterator.next()), charset));
                }

                continue;
            }

            parameters
                .add(URLEncoder.encode(parameter.getName(), charset)
                    + "="
                    + URLEncoder.encode(format(MEDIA_TYPE_TEXT_PLAIN, value), charset));
        }

        return parameters;
    }

    protected Charset getCharset()
    {
        return DEFAULT_CHARSET;
    }

}
