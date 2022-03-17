package at.porscheinformatik.happyrest.apache;

import java.util.Collection;
import java.util.List;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestException;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestRequestException;
import at.porscheinformatik.happyrest.RestResponse;
import pnet.data.api.util.Pair;

public class MockedApacheRestCall extends ApacheRestCall
{
    public MockedApacheRestCall(CloseableHttpClient httpClient, RestLoggerAdapter loggerAdapter, String url,
        List<MediaType> acceptableMediaTypes, MediaType contentType, List<RestAttribute> attributes,
        RestFormatter formatter, RestParser parser, Object body)
    {
        super(httpClient, loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter, parser, body);
    }

    @Override
    protected MockedApacheRestCall copy(String url, List<MediaType> acceptableMediaTypes, MediaType contentType,
        List<RestAttribute> attributes, RestFormatter formatter, Object body)
    {
        return new MockedApacheRestCall(httpClient, loggerAdapter, url, acceptableMediaTypes, contentType, attributes,
            formatter, parser, body);
    }

    @Override
    public MockedApacheRestCall acceptJson()
    {
        return (MockedApacheRestCall) super.acceptJson();
    }

    @Override
    public MockedApacheRestCall acceptXML()
    {
        return (MockedApacheRestCall) super.acceptXML();
    }

    @Override
    public MockedApacheRestCall basicAuthorization(String username, String password)
    {
        return (MockedApacheRestCall) super.basicAuthorization(username, password);
    }

    @Override
    public MockedApacheRestCall bearerAuthorization(String token)
    {
        return (MockedApacheRestCall) super.bearerAuthorization(token);
    }

    @Override
    public MockedApacheRestCall contentTypeJson()
    {
        return (MockedApacheRestCall) super.contentTypeJson();
    }

    @Override
    public MockedApacheRestCall contentTypeXML()
    {
        return (MockedApacheRestCall) super.contentTypeXML();
    }

    @Override
    public MockedApacheRestCall contentTypeForm()
    {
        return (MockedApacheRestCall) super.contentTypeForm();
    }

    @Override
    public MockedApacheRestCall url(String url)
    {
        return (MockedApacheRestCall) super.url(url);
    }

    @Override
    public MockedApacheRestCall path(String path)
    {
        return (MockedApacheRestCall) super.path(path);
    }

    @Override
    public MockedApacheRestCall pathSegment(String... pathSegments)
    {
        return (MockedApacheRestCall) super.pathSegment(pathSegments);
    }

    @Override
    public MockedApacheRestCall encodedPathSegment(String... pathSegments)
    {
        return (MockedApacheRestCall) super.encodedPathSegment(pathSegments);
    }

    @Override
    public MockedApacheRestCall accept(MediaType... mediaTypes)
    {
        return (MockedApacheRestCall) super.accept(mediaTypes);
    }

    @Override
    public MockedApacheRestCall header(String name, String... values)
    {
        return (MockedApacheRestCall) super.header(name, values);
    }

    @Override
    public MockedApacheRestCall headers(String name, Collection<String> values)
    {
        return (MockedApacheRestCall) super.headers(name, values);
    }

    @Override
    public MockedApacheRestCall variable(String name, Object... values)
    {
        return (MockedApacheRestCall) super.variable(name, values);
    }

    @Override
    public MockedApacheRestCall parameter(String name, Object... values)
    {
        return (MockedApacheRestCall) super.parameter(name, values);
    }

    @Override
    public MockedApacheRestCall parameters(String name, Collection<?> values)
    {
        return (MockedApacheRestCall) super.parameters(name, values);
    }

    @Override
    public MockedApacheRestCall parameters(Collection<? extends Pair<String, ?>> values)
    {
        return (MockedApacheRestCall) super.parameters(values);
    }

    @Override
    public MockedApacheRestCall formatter(RestFormatter formatter)
    {
        return (MockedApacheRestCall) super.formatter(formatter);
    }

    @Override
    public MockedApacheRestCall contentType(MediaType contentType)
    {
        return (MockedApacheRestCall) super.contentType(contentType);
    }

    @Override
    public MockedApacheRestCall body(Object body)
    {
        return (MockedApacheRestCall) super.body(body);
    }

    @Override
    public <T> MockedApacheRestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException
    {
        return (MockedApacheRestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    public <T> MockedApacheRestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException
    {
        return (MockedApacheRestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    protected <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType, HttpRequestBase request)
        throws RestException, RestRequestException
    {
        return new MockedApacheRestResponse<>(responseType, request);
    }

}
