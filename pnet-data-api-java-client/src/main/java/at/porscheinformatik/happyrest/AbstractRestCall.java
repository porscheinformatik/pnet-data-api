package at.porscheinformatik.happyrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A REST call. Objects of this type must be final and thread-safe!
 *
 * @author ham
 */
public abstract class AbstractRestCall implements RestCall
{

    private final String url;
    private final List<String> acceptableMediaTypes;
    private final Object body;
    private final List<RestAttribute> attributes;

    protected AbstractRestCall(String url, List<String> acceptableMediaTypes, List<RestAttribute> attributes,
        Object body)
    {
        super();

        this.url = url;
        this.acceptableMediaTypes = acceptableMediaTypes;
        this.attributes = attributes;
        this.body = body;
    }

    protected abstract RestCall copy(String url, List<String> acceptableMediaTypes, List<RestAttribute> attributes,
        Object body);

    @Override
    public String getUrl()
    {
        return url;
    }

    @Override
    public RestCall url(String url)
    {
        return copy(url, acceptableMediaTypes, attributes, body);
    }

    @Override
    public List<String> getAcceptableMediaTypes()
    {
        return acceptableMediaTypes;
    }

    @Override
    public RestCall accept(String... mediaTypes)
    {
        ArrayList<String> acceptableMediaTypes = new ArrayList<>(Arrays.asList(mediaTypes));

        if (this.acceptableMediaTypes != null)
        {
            acceptableMediaTypes.addAll(0, this.acceptableMediaTypes);
        }

        return copy(url, Collections.unmodifiableList(acceptableMediaTypes), attributes, body);
    }

    @Override
    public List<RestAttribute> getAttributes()
    {
        return attributes;
    }

    @Override
    public RestCall header(String name, String value)
    {
        return attribute(RestAttribute.header(name, value));
    }

    @Override
    public RestCall variable(String name, Object value)
    {
        return attribute(RestAttribute.variable(name, value));
    }

    @Override
    public RestCall parameter(String name, Object value)
    {
        return attribute(RestAttribute.parameter(name, value));
    }

    protected RestCall attribute(RestAttribute... attributesToAdd)
    {
        ArrayList<RestAttribute> attributes = new ArrayList<>(Arrays.asList(attributesToAdd));

        if (this.attributes != null)
        {
            attributes.addAll(0, this.attributes);
        }

        return copy(url, acceptableMediaTypes, Collections.unmodifiableList(attributes), body);
    }

    //    @Override
    //    public RestCall basicAuthorization(UsernamePasswordCredentials credentials)
    //    {
    //        return header("Authorization", BasicScheme.authenticate(credentials, Charset.forName("UTF-8").name()));
    //    }

    public Object getBody()
    {
        return body;
    }

    @Override
    public RestCall body(Object body)
    {
        return copy(url, acceptableMediaTypes, attributes, body);
    }

    @Override
    public <T> T get(Class<T> responseType)
    {
        return invoke(RestMethod.GET, null, responseType).getBody();
    }

    @Override
    public <T> T get(String path, Class<T> responseType)
    {
        return invoke(RestMethod.GET, path, responseType).getBody();
    }

    @Override
    public <T> T get(GenericType<T> responseType)
    {
        return invoke(RestMethod.GET, null, responseType).getBody();
    }

    @Override
    public <T> T get(String path, GenericType<T> responseType)
    {
        return invoke(RestMethod.GET, path, responseType).getBody();
    }

    @Override
    public <T> T put(Class<T> responseType)
    {
        return invoke(RestMethod.PUT, null, responseType).getBody();
    }

    @Override
    public <T> T put(String path, Class<T> responseType)
    {
        return invoke(RestMethod.PUT, path, responseType).getBody();
    }

    @Override
    public <T> T put(GenericType<T> responseType)
    {
        return invoke(RestMethod.PUT, null, responseType).getBody();
    }

    @Override
    public <T> T put(String path, GenericType<T> responseType)
    {
        return invoke(RestMethod.PUT, path, responseType).getBody();
    }

    @Override
    public <T> T post(Class<T> responseType)
    {
        return invoke(RestMethod.POST, null, responseType).getBody();
    }

    @Override
    public <T> T post(String path, Class<T> responseType)
    {
        return invoke(RestMethod.POST, path, responseType).getBody();
    }

    @Override
    public <T> T post(GenericType<T> responseType)
    {
        return invoke(RestMethod.POST, null, responseType).getBody();
    }

    @Override
    public <T> T post(String path, GenericType<T> responseType)
    {
        return invoke(RestMethod.POST, path, responseType).getBody();
    }

    @Override
    public <T> T delete(Class<T> responseType)
    {
        return invoke(RestMethod.DELETE, null, responseType).getBody();
    }

    @Override
    public <T> T delete(String path, Class<T> responseType)
    {
        return invoke(RestMethod.DELETE, path, responseType).getBody();
    }

    @Override
    public <T> T delete(GenericType<T> responseType)
    {
        return invoke(RestMethod.DELETE, null, responseType).getBody();
    }

    @Override
    public <T> T delete(String path, GenericType<T> responseType)
    {
        return invoke(RestMethod.DELETE, path, responseType).getBody();
    }

    @Override
    public <T> T options(Class<T> responseType)
    {
        return invoke(RestMethod.OPTIONS, null, responseType).getBody();
    }

    @Override
    public <T> T options(String path, Class<T> responseType)
    {
        return invoke(RestMethod.OPTIONS, path, responseType).getBody();
    }

    @Override
    public <T> T options(GenericType<T> responseType)
    {
        return invoke(RestMethod.OPTIONS, null, responseType).getBody();
    }

    @Override
    public <T> T options(String path, GenericType<T> responseType)
    {
        return invoke(RestMethod.OPTIONS, path, responseType).getBody();
    }

    @Override
    public <T> T patch(Class<T> responseType)
    {
        return invoke(RestMethod.PATCH, null, responseType).getBody();
    }

    @Override
    public <T> T patch(String path, Class<T> responseType)
    {
        return invoke(RestMethod.PATCH, path, responseType).getBody();
    }

    @Override
    public <T> T patch(GenericType<T> responseType)
    {
        return invoke(RestMethod.PATCH, null, responseType).getBody();
    }

    @Override
    public <T> T patch(String path, GenericType<T> responseType)
    {
        return invoke(RestMethod.PATCH, path, responseType).getBody();
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType)
    {
        return invoke(method, null, responseType);
    }

    @Override
    public <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType)
    {
        return invoke(method, null, responseType);
    }

    @Override
    public abstract <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType);

    @Override
    public abstract <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType);

}
