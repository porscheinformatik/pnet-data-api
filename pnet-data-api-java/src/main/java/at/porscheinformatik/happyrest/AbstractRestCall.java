package at.porscheinformatik.happyrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import pnet.data.api.util.Pair;

/**
 * A REST call. Objects of this type must be final and thread-safe!
 *
 * @author ham
 */
public abstract class AbstractRestCall implements RestCall
{

    private final String url;
    private final List<String> acceptableMediaTypes;
    private final String contentType;
    private final List<RestAttribute> attributes;
    private final RestFormatter formatter;
    private final Object body;

    protected AbstractRestCall(String url, List<String> acceptableMediaTypes, String contentType,
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

    protected abstract RestCall copy(String url, List<String> acceptableMediaTypes, String contentType,
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
    public RestCall pathSegment(String... pathSegments)
    {
        return copy(RestUtils
            .appendPathSegmentsWithPlaceholders(Objects.requireNonNull(url, "Cannot add path to missing URL"),
                pathSegments),
            acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    public RestCall encodedPathSegment(String... pathSegments)
    {
        return copy(
            RestUtils
                .appendEncodedPathSegments(Objects.requireNonNull(url, "Cannot add path to missing URL"), pathSegments),
            acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    public List<String> getAcceptableMediaTypes()
    {
        return acceptableMediaTypes;
    }

    @Override
    public RestCall accept(String... mediaTypes)
    {
        ArrayList<String> currentAcceptableMediaTypes = new ArrayList<>(Arrays.asList(mediaTypes));

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

    protected String format(String contentType, Object value)
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

    public String getContentType()
    {
        return contentType;
    }

    @Override
    public RestCall contentType(String contentType)
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
        String contentType = getContentType();

        return contentType != null && contentType.startsWith(MEDIA_TYPE_APPLICATION_FORM) && containsParameters();
    }

    protected boolean verify(RestMethod method) throws RestRequestException
    {
        if (body != null && (method == RestMethod.GET || method == RestMethod.OPTIONS))
        {
            throw new RestRequestException("A %s request does not allow a body.", method);
        }

        boolean form = isForm();

        if (form
            && (method == RestMethod.GET
                || method == RestMethod.PUT
                || method == RestMethod.DELETE
                || method == RestMethod.OPTIONS))
        {
            throw new RestRequestException("A %s request does not allow form values.", method);
        }

        return form;
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException
    {
        return invoke(method, null, responseType);
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException
    {
        return invoke(method, null, responseType);
    }

    @Override
    public abstract <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType)
        throws RestException;

    @Override
    public abstract <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType)
        throws RestException;

}
