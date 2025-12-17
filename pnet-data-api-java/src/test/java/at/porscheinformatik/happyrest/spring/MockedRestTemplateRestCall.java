package at.porscheinformatik.happyrest.spring;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestAttribute;
import at.porscheinformatik.happyrest.RestException;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import pnet.data.api.util.Pair;

public class MockedRestTemplateRestCall extends RestTemplateRestCall {

    public MockedRestTemplateRestCall(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter,
        String url
    ) {
        super(restTemplate, loggerAdapter, formatter, url);
    }

    public MockedRestTemplateRestCall(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter
    ) {
        super(restTemplate, loggerAdapter, formatter);
    }

    public MockedRestTemplateRestCall(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        Object body
    ) {
        super(restTemplate, loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter, body);
    }

    @Override
    protected MockedRestTemplateRestCall copy(
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        Object body
    ) {
        return new MockedRestTemplateRestCall(
            restTemplate,
            loggerAdapter,
            url,
            acceptableMediaTypes,
            contentType,
            attributes,
            formatter,
            body
        );
    }

    @Override
    public MockedRestTemplateRestCall acceptJson() {
        return (MockedRestTemplateRestCall) super.acceptJson();
    }

    @Override
    public MockedRestTemplateRestCall acceptXML() {
        return (MockedRestTemplateRestCall) super.acceptXML();
    }

    @Override
    public MockedRestTemplateRestCall basicAuthorization(String username, String password) {
        return (MockedRestTemplateRestCall) super.basicAuthorization(username, password);
    }

    @Override
    public MockedRestTemplateRestCall bearerAuthorization(String token) {
        return (MockedRestTemplateRestCall) super.bearerAuthorization(token);
    }

    @Override
    public MockedRestTemplateRestCall contentTypeJson() {
        return (MockedRestTemplateRestCall) super.contentTypeJson();
    }

    @Override
    public MockedRestTemplateRestCall contentTypeXML() {
        return (MockedRestTemplateRestCall) super.contentTypeXML();
    }

    @Override
    public MockedRestTemplateRestCall contentTypeForm() {
        return (MockedRestTemplateRestCall) super.contentTypeForm();
    }

    @Override
    public MockedRestTemplateRestCall url(String url) {
        return (MockedRestTemplateRestCall) super.url(url);
    }

    @Override
    public MockedRestTemplateRestCall path(String path) {
        return (MockedRestTemplateRestCall) super.path(path);
    }

    @Override
    public MockedRestTemplateRestCall pathSegment(String... pathSegments) {
        return (MockedRestTemplateRestCall) super.pathSegment(pathSegments);
    }

    @Override
    public MockedRestTemplateRestCall encodedPathSegment(String... pathSegments) {
        return (MockedRestTemplateRestCall) super.encodedPathSegment(pathSegments);
    }

    @Override
    public MockedRestTemplateRestCall accept(MediaType... mediaTypes) {
        return (MockedRestTemplateRestCall) super.accept(mediaTypes);
    }

    @Override
    public MockedRestTemplateRestCall header(String name, String... values) {
        return (MockedRestTemplateRestCall) super.header(name, values);
    }

    @Override
    public MockedRestTemplateRestCall headers(String name, Collection<String> values) {
        return (MockedRestTemplateRestCall) super.headers(name, values);
    }

    @Override
    public MockedRestTemplateRestCall variable(String name, Object... values) {
        return (MockedRestTemplateRestCall) super.variable(name, values);
    }

    @Override
    public MockedRestTemplateRestCall parameter(String name, Object... values) {
        return (MockedRestTemplateRestCall) super.parameter(name, values);
    }

    @Override
    public MockedRestTemplateRestCall parameters(String name, Collection<?> values) {
        return (MockedRestTemplateRestCall) super.parameters(name, values);
    }

    @Override
    public MockedRestTemplateRestCall parameters(Collection<? extends Pair<String, ?>> values) {
        return (MockedRestTemplateRestCall) super.parameters(values);
    }

    @Override
    public MockedRestTemplateRestCall formatter(RestFormatter formatter) {
        return (MockedRestTemplateRestCall) super.formatter(formatter);
    }

    @Override
    public MockedRestTemplateRestCall contentType(MediaType contentType) {
        return (MockedRestTemplateRestCall) super.contentType(contentType);
    }

    @Override
    public MockedRestTemplateRestCall body(Object body) {
        return (MockedRestTemplateRestCall) super.body(body);
    }

    @Override
    public <T> MockedRestTemplateRestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException {
        return (MockedRestTemplateRestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    public <T> MockedRestTemplateRestResponse<T> invoke(RestMethod method, GenericType<T> responseType)
        throws RestException {
        return (MockedRestTemplateRestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    protected <T> MockedRestTemplateRestResponse<T> invoke(
        RestMethod method,
        Class<T> responseType,
        URI uri,
        HttpEntity<Object> entity
    ) throws RestException {
        return new MockedRestTemplateRestResponse<>(method, GenericType.of(responseType), uri, entity);
    }

    @Override
    protected <T> RestResponse<T> invoke(
        RestMethod method,
        GenericType<T> responseType,
        URI uri,
        HttpEntity<Object> entity
    ) throws RestException {
        return new MockedRestTemplateRestResponse<>(method, responseType, uri, entity);
    }
}
