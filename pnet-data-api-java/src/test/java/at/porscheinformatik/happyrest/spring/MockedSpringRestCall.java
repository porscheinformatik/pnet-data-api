package at.porscheinformatik.happyrest.spring;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestException;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestResponseException;
import pnet.data.api.util.Pair;

public class MockedSpringRestCall extends SpringRestCall
{
    public MockedSpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter,
        String url)
    {
        super(restTemplate, loggerAdapter, formatter, url);
    }

    public MockedSpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, RestFormatter formatter)
    {
        super(restTemplate, loggerAdapter, formatter);
    }

    public MockedSpringRestCall(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter, String url,
        List<MediaType> acceptableMediaTypes, MediaType contentType, List<RestAttribute> attributes,
        RestFormatter formatter, Object body)
    {
        super(restTemplate, loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    protected MockedSpringRestCall copy(RestLoggerAdapter loggerAdapter, String url,
        List<MediaType> acceptableMediaTypes, MediaType contentType, List<RestAttribute> attributes,
        RestFormatter formatter, Object body)
    {
        return new MockedSpringRestCall(restTemplate, loggerAdapter, url, acceptableMediaTypes, contentType, attributes,
            formatter, body);
    }

    @Override
    public MockedSpringRestCall acceptJson()
    {
        return (MockedSpringRestCall) super.acceptJson();
    }

    @Override
    public MockedSpringRestCall acceptXML()
    {
        return (MockedSpringRestCall) super.acceptXML();
    }

    @Override
    public MockedSpringRestCall basicAuthorization(String username, String password)
    {
        return (MockedSpringRestCall) super.basicAuthorization(username, password);
    }

    @Override
    public MockedSpringRestCall bearerAuthorization(String token)
    {
        return (MockedSpringRestCall) super.bearerAuthorization(token);
    }

    @Override
    public MockedSpringRestCall contentTypeJson()
    {
        return (MockedSpringRestCall) super.contentTypeJson();
    }

    @Override
    public MockedSpringRestCall contentTypeXML()
    {
        return (MockedSpringRestCall) super.contentTypeXML();
    }

    @Override
    public MockedSpringRestCall contentTypeForm()
    {
        return (MockedSpringRestCall) super.contentTypeForm();
    }

    @Override
    public MockedSpringRestCall url(String url)
    {
        return (MockedSpringRestCall) super.url(url);
    }

    @Override
    public MockedSpringRestCall path(String path)
    {
        return (MockedSpringRestCall) super.path(path);
    }

    @Override
    public MockedSpringRestCall pathSegment(String... pathSegments)
    {
        return (MockedSpringRestCall) super.pathSegment(pathSegments);
    }

    @Override
    public MockedSpringRestCall encodedPathSegment(String... pathSegments)
    {
        return (MockedSpringRestCall) super.encodedPathSegment(pathSegments);
    }

    @Override
    public MockedSpringRestCall accept(MediaType... mediaTypes)
    {
        return (MockedSpringRestCall) super.accept(mediaTypes);
    }

    @Override
    public MockedSpringRestCall header(String name, String... values)
    {
        return (MockedSpringRestCall) super.header(name, values);
    }

    @Override
    public MockedSpringRestCall headers(String name, Collection<String> values)
    {
        return (MockedSpringRestCall) super.headers(name, values);
    }

    @Override
    public MockedSpringRestCall variable(String name, Object... values)
    {
        return (MockedSpringRestCall) super.variable(name, values);
    }

    @Override
    public MockedSpringRestCall parameter(String name, Object... values)
    {
        return (MockedSpringRestCall) super.parameter(name, values);
    }

    @Override
    public MockedSpringRestCall parameters(String name, Collection<?> values)
    {
        return (MockedSpringRestCall) super.parameters(name, values);
    }

    @Override
    public MockedSpringRestCall parameters(Collection<? extends Pair<String, ?>> values)
    {
        return (MockedSpringRestCall) super.parameters(values);
    }

    @Override
    public MockedSpringRestCall formatter(RestFormatter formatter)
    {
        return (MockedSpringRestCall) super.formatter(formatter);
    }

    @Override
    public MockedSpringRestCall contentType(MediaType contentType)
    {
        return (MockedSpringRestCall) super.contentType(contentType);
    }

    @Override
    public MockedSpringRestCall body(Object body)
    {
        return (MockedSpringRestCall) super.body(body);
    }

    @Override
    public <T> MockedSpringRestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException
    {
        return (MockedSpringRestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    public <T> MockedSpringRestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException
    {
        return (MockedSpringRestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    protected <T> MockedSpringRestResponse<T> invoke(RestMethod method, Class<T> responseType, URI uri,
        HttpEntity<Object> entity) throws RestResponseException, RestException
    {
        return new MockedSpringRestResponse<>(method, GenericType.of(responseType), uri, entity);
    }

    @Override
    protected <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType, URI uri,
        HttpEntity<Object> entity) throws RestResponseException, RestException
    {
        return new MockedSpringRestResponse<>(method, responseType, uri, entity);
    }
}
