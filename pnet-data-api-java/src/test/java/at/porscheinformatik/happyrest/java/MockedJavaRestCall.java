package at.porscheinformatik.happyrest.java;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.List;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestException;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestRequestException;
import pnet.data.api.util.Pair;

public class MockedJavaRestCall extends JavaRestCall
{
    public MockedJavaRestCall(HttpClient httpClient, String userAgent, RestLoggerAdapter loggerAdapter, String url,
        List<MediaType> acceptableMediaTypes, MediaType contentType, List<RestAttribute> attributes,
        RestFormatter formatter, RestParser parser, Object body)
    {
        super(httpClient, userAgent, loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter,
            parser, body);
    }

    @Override
    protected MockedJavaRestCall copy(String url, List<MediaType> acceptableMediaTypes, MediaType contentType,
        List<RestAttribute> attributes, RestFormatter formatter, Object body)
    {
        return new MockedJavaRestCall(httpClient, userAgent, loggerAdapter, url, acceptableMediaTypes, contentType,
            attributes, formatter, parser, body);
    }

    @Override
    public MockedJavaRestCall acceptJson()
    {
        return (MockedJavaRestCall) super.acceptJson();
    }

    @Override
    public MockedJavaRestCall acceptXML()
    {
        return (MockedJavaRestCall) super.acceptXML();
    }

    @Override
    public MockedJavaRestCall basicAuthorization(String username, String password)
    {
        return (MockedJavaRestCall) super.basicAuthorization(username, password);
    }

    @Override
    public MockedJavaRestCall bearerAuthorization(String token)
    {
        return (MockedJavaRestCall) super.bearerAuthorization(token);
    }

    @Override
    public MockedJavaRestCall contentTypeJson()
    {
        return (MockedJavaRestCall) super.contentTypeJson();
    }

    @Override
    public MockedJavaRestCall contentTypeXML()
    {
        return (MockedJavaRestCall) super.contentTypeXML();
    }

    @Override
    public MockedJavaRestCall contentTypeForm()
    {
        return (MockedJavaRestCall) super.contentTypeForm();
    }

    @Override
    public MockedJavaRestCall url(String url)
    {
        return (MockedJavaRestCall) super.url(url);
    }

    @Override
    public MockedJavaRestCall path(String path)
    {
        return (MockedJavaRestCall) super.path(path);
    }

    @Override
    public MockedJavaRestCall pathSegment(String... pathSegments)
    {
        return (MockedJavaRestCall) super.pathSegment(pathSegments);
    }

    @Override
    public MockedJavaRestCall encodedPathSegment(String... pathSegments)
    {
        return (MockedJavaRestCall) super.encodedPathSegment(pathSegments);
    }

    @Override
    public MockedJavaRestCall accept(MediaType... mediaTypes)
    {
        return (MockedJavaRestCall) super.accept(mediaTypes);
    }

    @Override
    public MockedJavaRestCall header(String name, String... values)
    {
        return (MockedJavaRestCall) super.header(name, values);
    }

    @Override
    public MockedJavaRestCall headers(String name, Collection<String> values)
    {
        return (MockedJavaRestCall) super.headers(name, values);
    }

    @Override
    public MockedJavaRestCall variable(String name, Object... values)
    {
        return (MockedJavaRestCall) super.variable(name, values);
    }

    @Override
    public MockedJavaRestCall parameter(String name, Object... values)
    {
        return (MockedJavaRestCall) super.parameter(name, values);
    }

    @Override
    public MockedJavaRestCall parameters(String name, Collection<?> values)
    {
        return (MockedJavaRestCall) super.parameters(name, values);
    }

    @Override
    public MockedJavaRestCall parameters(Collection<? extends Pair<String, ?>> values)
    {
        return (MockedJavaRestCall) super.parameters(values);
    }

    @Override
    public MockedJavaRestCall formatter(RestFormatter formatter)
    {
        return (MockedJavaRestCall) super.formatter(formatter);
    }

    @Override
    public MockedJavaRestCall contentType(MediaType contentType)
    {
        return (MockedJavaRestCall) super.contentType(contentType);
    }

    @Override
    public MockedJavaRestCall body(Object body)
    {
        return (MockedJavaRestCall) super.body(body);
    }

    @Override
    public <T> MockedJavaRestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException
    {
        return (MockedJavaRestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    public <T> MockedJavaRestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException
    {
        return (MockedJavaRestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    protected <T> MockedJavaRestResponse<T> invoke(HttpRequest request, GenericType<T> responseType)
        throws RestRequestException, RestException
    {
        return new MockedJavaRestResponse<>(responseType, request);
    }
}
