package at.porscheinformatik.happyrest;

import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import pnet.data.api.util.Pair;

/**
 * A REST call. Objects of this type must be final and thread-safe!
 *
 * @author ham
 */
public abstract class AbstractRestCall implements RestCall
{

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private final String url;
    private final List<MediaType> acceptableMediaTypes;
    private final MediaType contentType;
    private final List<RestAttribute> attributes;
    private final RestFormatter formatter;
    private final Object body;

    protected AbstractRestCall(String url, List<MediaType> acceptableMediaTypes, MediaType contentType,
        List<RestAttribute> attributes, RestFormatter formatter, Object body)
    {
        super();

        this.url = url;
        this.acceptableMediaTypes = acceptableMediaTypes;
        this.contentType = contentType;
        this.attributes = attributes == null ? new ArrayList<>() : attributes;
        this.formatter = formatter;
        this.body = body;
    }

    protected abstract RestCall copy(String url, List<MediaType> acceptableMediaTypes, MediaType contentType,
        List<RestAttribute> attributes, RestFormatter formatter, Object body);

    @Override
    public String getUrl()
    {
        return url;
    }

    @Override
    public RestCall url(String url)
    {
        return copy(url, acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    public RestCall path(String path)
    {
        if (path == null || path.isEmpty())
        {
            return this;
        }

        return copy(
            RestUtils.appendPathWithPlaceholders(Objects.requireNonNull(url, "Cannot add path to missing URL"), path),
            acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    public RestCall pathSegment(String... pathSegments)
    {
        if (pathSegments == null || pathSegments.length == 0)
        {
            return this;
        }

        return copy(RestUtils
            .appendPathSegmentsWithPlaceholders(Objects.requireNonNull(url, "Cannot add path to missing URL"),
                pathSegments),
            acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    public RestCall encodedPathSegment(String... pathSegments)
    {
        if (pathSegments == null || pathSegments.length == 0)
        {
            return this;
        }

        return copy(
            RestUtils
                .appendEncodedPathSegments(Objects.requireNonNull(url, "Cannot add path to missing URL"), pathSegments),
            acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    public List<MediaType> getAcceptableMediaTypes()
    {
        return acceptableMediaTypes;
    }

    @Override
    public RestCall accept(MediaType... mediaTypes)
    {
        ArrayList<MediaType> currentAcceptableMediaTypes = new ArrayList<>(Arrays.asList(mediaTypes));

        if (acceptableMediaTypes != null)
        {
            currentAcceptableMediaTypes.addAll(0, acceptableMediaTypes);
        }

        return copy(url, Collections.unmodifiableList(currentAcceptableMediaTypes), contentType, attributes, formatter,
            body);
    }

    @Override
    public List<RestAttribute> getAttributes()
    {
        return attributes;
    }

    @Override
    public RestCall header(String name, String... values)
    {
        if (values == null || values.length == 0)
        {
            return this;
        }

        return attribute(Arrays
            .stream(values)
            .map(value -> RestAttribute.header(name, value))
            .toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall headers(String name, Collection<String> values)
    {
        if (values == null || values.isEmpty())
        {
            return this;
        }

        return attribute(
            values.stream().map(value -> RestAttribute.header(name, value)).toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall variable(String name, Object... values)
    {
        if (values == null || values.length == 0)
        {
            return this;
        }

        return attribute(Arrays
            .stream(values)
            .map(value -> RestAttribute.variable(name, value))
            .toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall parameter(String name, Object... values)
    {
        if (values == null || values.length == 0)
        {
            return this;
        }

        return attribute(Arrays
            .stream(values)
            .map(value -> RestAttribute.parameter(name, value))
            .toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall parameters(String name, Collection<?> values)
    {
        if (values == null || values.isEmpty())
        {
            return this;
        }

        return attribute(values
            .stream()
            .map(value -> RestAttribute.parameter(name, value))
            .toArray(size -> new RestAttribute[size]));
    }

    @Override
    public RestCall parameters(Collection<? extends Pair<String, ?>> values)
    {
        if (values == null || values.isEmpty())
        {
            return this;
        }

        return attribute(values
            .stream()
            .map(value -> RestAttribute.parameter(value.getLeft(), value.getRight()))
            .toArray(size -> new RestAttribute[size]));
    }

    protected RestCall attribute(RestAttribute... attributesToAdd)
    {
        ArrayList<RestAttribute> currentAttributes = new ArrayList<>(Arrays.asList(attributesToAdd));

        if (attributes != null)
        {
            currentAttributes.addAll(0, attributes);
        }

        return copy(url, acceptableMediaTypes, contentType, Collections.unmodifiableList(currentAttributes), formatter,
            body);
    }

    protected RestFormatter getFormatter()
    {
        return formatter;
    }

    protected String format(MediaType contentType, Object value)
    {
        try
        {
            return getFormatter().format(contentType, value);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Failed to convert " + value + " to " + contentType, e);
        }
    }

    public RestCall formatter(RestFormatter formatter)
    {
        return copy(url, acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    public MediaType getContentType()
    {
        return contentType;
    }

    @Override
    public RestCall contentType(MediaType contentType)
    {
        return copy(url, acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    public Object getBody()
    {
        return body;
    }

    @Override
    public RestCall body(Object body)
    {
        return copy(url, acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    protected boolean isForm()
    {
        return MediaType.APPLICATION_FORM.isCompatible(getContentType()) && containsParameters();
    }

    protected boolean verify(RestMethod method) throws RestRequestException
    {
        if (body != null && (method == RestMethod.GET || method == RestMethod.OPTIONS))
        {
            throw new RestRequestException("A " + method + " request does not allow a body.");
        }

        boolean form = isForm();

        if (form
            && (method == RestMethod.GET
                || method == RestMethod.PUT
                || method == RestMethod.DELETE
                || method == RestMethod.OPTIONS))
        {
            throw new RestRequestException("A " + method + " request does not allow form values.");
        }

        return form;
    }

    @Override
    public abstract <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException;

    @Override
    public abstract <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException;

    @Override
    public final <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType) throws RestException
    {
        return path(path).invoke(method, responseType);
    }

    @Override
    public final <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType)
        throws RestException
    {
        return path(path).invoke(method, responseType);
    }

    protected Charset getCharset()
    {
        return DEFAULT_CHARSET;
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
                            + URLEncoder.encode(format(MediaType.TEXT_PLAIN, Array.get(value, i)), charset));
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
                            + URLEncoder.encode(format(MediaType.TEXT_PLAIN, iterator.next()), charset));
                }

                continue;
            }

            parameters
                .add(URLEncoder.encode(parameter.getName(), charset)
                    + "="
                    + URLEncoder.encode(format(MediaType.TEXT_PLAIN, value), charset));
        }

        return parameters;
    }

    protected String buildUrl(boolean form)
    {
        String url = getUrl();

        for (RestVariable variable : getVariables())
        {
            url = url.replace("{" + variable.getName() + "}", format(MediaType.TEXT_PLAIN, variable.getValue()));
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

    @Override
    public String toString()
    {
        return buildUrl(false);
    }
}
