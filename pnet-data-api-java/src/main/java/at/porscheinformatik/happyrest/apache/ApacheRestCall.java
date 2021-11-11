package at.porscheinformatik.happyrest.apache;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

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

/**
 * A REST call. This implementation is thread-safe!
 *
 * @author HAM
 */
public class ApacheRestCall extends AbstractRestCall
{

    private final CloseableHttpClient httpClient;
    private final RestLoggerAdapter loggerAdapter;
    private final RestParser parser;

    public ApacheRestCall(CloseableHttpClient httpClient, RestLoggerAdapter loggerAdapter, String url,
        List<MediaType> acceptableMediaTypes, MediaType contentType, List<RestAttribute> attributes,
        RestFormatter formatter, RestParser parser, Object body)
    {
        super(url, acceptableMediaTypes, contentType, attributes, formatter, body);

        this.httpClient = httpClient;

        this.loggerAdapter = loggerAdapter;
        this.parser = parser;
    }

    protected HttpEntity createEntity(MediaType contentType) throws UnsupportedEncodingException
    {
        Object body = getBody();

        if (body != null)
        {
            return new StringEntity(format(contentType, body), ContentType.parse(contentType.toString()));
        }

        if (isForm())
        {
            List<NameValuePair> params = new ArrayList<>();

            getParameters()
                .forEach(parameter -> params
                    .add(new BasicNameValuePair(parameter.getName(),
                        format(MediaType.TEXT_PLAIN, parameter.getValue()))));

            return new UrlEncodedFormEntity(params);
        }

        return null;
    }

    @Override
    protected RestCall copy(String url, List<MediaType> acceptableMediaTypes, MediaType contentType,
        List<RestAttribute> attributes, RestFormatter formatter, Object body)
    {
        return new ApacheRestCall(httpClient, loggerAdapter, url, acceptableMediaTypes, contentType, attributes,
            formatter, parser, body);
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
        String url = buildUrl(path);

        HttpRequestBase request = buildRequest(method, url, form);

        computeHeaders(request);
        computeEntity(method, request);

        loggerAdapter.logRequest(method, String.valueOf(request.getURI()));

        try (CloseableHttpResponse response = httpClient.execute(request))
        {
            return ApacheRestResponse.create(parser, response, responseType);
        }
        catch (IOException e)
        {
            throw new RestRequestException("Request failed", e);
        }
    }

    private String buildUrl(String path)
    {
        String url = RestUtils.appendPathWithPlaceholders(getUrl(), path);

        for (RestVariable variable : getVariables())
        {
            url = url.replace("{" + variable.getName() + "}", format(MediaType.TEXT_PLAIN, variable.getValue()));
        }

        return url;
    }

    private HttpRequestBase buildRequest(RestMethod method, String url, boolean form) throws RestRequestException
    {
        HttpRequestBase request;

        try
        {
            URIBuilder builder = new URIBuilder(url);

            if (!form)
            {
                populateWithParameters(builder);
            }

            switch (method)
            {
                case GET:
                    request = new HttpGet(builder.build());
                    break;

                case POST:
                    request = new HttpPost(builder.build());
                    break;

                case PUT:
                    request = new HttpPut(builder.build());
                    break;

                case DELETE:
                    request = new HttpDelete(builder.build());
                    break;

                case OPTIONS:
                    request = new HttpOptions(builder.build());
                    break;

                case PATCH:
                    request = new HttpPatch(builder.build());
                    break;

                default:
                    throw new UnsupportedOperationException("Unsupported method: " + method);
            }
        }
        catch (URISyntaxException e)
        {
            throw new RestRequestException("Invalid URL: %s", e, url);
        }
        return request;
    }

    private void populateWithParameters(URIBuilder builder)
    {
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
                    builder.addParameter(parameter.getName(), format(MediaType.TEXT_PLAIN, Array.get(value, i)));
                }

                continue;
            }

            if (value instanceof Iterable<?>)
            {
                Iterator<?> iterator = ((Iterable<?>) value).iterator();

                while (iterator.hasNext())
                {
                    builder.addParameter(parameter.getName(), format(MediaType.TEXT_PLAIN, iterator.next()));
                }

                continue;
            }

            builder.addParameter(parameter.getName(), format(MediaType.TEXT_PLAIN, value));
        }
    }

    private void computeHeaders(HttpRequestBase request)
    {
        getHeaders()
            .forEach(header -> request.addHeader(header.getName(), format(MediaType.TEXT_PLAIN, header.getValue())));

        MediaType contentType = getContentType();

        if (contentType != null)
        {
            request.addHeader("Content-Type", contentType.toString());
        }
    }

    private void computeEntity(RestMethod method, HttpRequestBase request) throws RestRequestException
    {
        HttpEntity requestEntity;

        try
        {
            requestEntity = createEntity(getContentType());
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RestRequestException("Failed to encode request", e);
        }

        if (requestEntity != null)
        {
            if (!(request instanceof HttpEntityEnclosingRequestBase))
            {
                throw new UnsupportedOperationException("A body in a " + method + " request is not supported");
            }

            ((HttpEntityEnclosingRequestBase) request).setEntity(requestEntity);
        }
    }
}
