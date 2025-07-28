package at.porscheinformatik.happyrest.apache5;

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
import java.util.Collection;
import java.util.List;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import pnet.data.api.util.Pair;

public class MockedApache5RestCall extends Apache5RestCall {

    public MockedApache5RestCall(
        CloseableHttpClient httpClient,
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        RestParser parser,
        Object body
    ) {
        super(httpClient, loggerAdapter, url, acceptableMediaTypes, contentType, attributes, formatter, parser, body);
    }

    @Override
    protected MockedApache5RestCall copy(
        RestLoggerAdapter loggerAdapter,
        String url,
        List<MediaType> acceptableMediaTypes,
        MediaType contentType,
        List<RestAttribute> attributes,
        RestFormatter formatter,
        Object body
    ) {
        return new MockedApache5RestCall(
            httpClient,
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
    public MockedApache5RestCall acceptJson() {
        return (MockedApache5RestCall) super.acceptJson();
    }

    @Override
    public MockedApache5RestCall acceptXML() {
        return (MockedApache5RestCall) super.acceptXML();
    }

    @Override
    public MockedApache5RestCall basicAuthorization(String username, String password) {
        return (MockedApache5RestCall) super.basicAuthorization(username, password);
    }

    @Override
    public MockedApache5RestCall bearerAuthorization(String token) {
        return (MockedApache5RestCall) super.bearerAuthorization(token);
    }

    @Override
    public MockedApache5RestCall contentTypeJson() {
        return (MockedApache5RestCall) super.contentTypeJson();
    }

    @Override
    public MockedApache5RestCall contentTypeXML() {
        return (MockedApache5RestCall) super.contentTypeXML();
    }

    @Override
    public MockedApache5RestCall contentTypeForm() {
        return (MockedApache5RestCall) super.contentTypeForm();
    }

    @Override
    public MockedApache5RestCall url(String url) {
        return (MockedApache5RestCall) super.url(url);
    }

    @Override
    public MockedApache5RestCall path(String path) {
        return (MockedApache5RestCall) super.path(path);
    }

    @Override
    public MockedApache5RestCall pathSegment(String... pathSegments) {
        return (MockedApache5RestCall) super.pathSegment(pathSegments);
    }

    @Override
    public MockedApache5RestCall encodedPathSegment(String... pathSegments) {
        return (MockedApache5RestCall) super.encodedPathSegment(pathSegments);
    }

    @Override
    public MockedApache5RestCall accept(MediaType... mediaTypes) {
        return (MockedApache5RestCall) super.accept(mediaTypes);
    }

    @Override
    public MockedApache5RestCall header(String name, String... values) {
        return (MockedApache5RestCall) super.header(name, values);
    }

    @Override
    public MockedApache5RestCall headers(String name, Collection<String> values) {
        return (MockedApache5RestCall) super.headers(name, values);
    }

    @Override
    public MockedApache5RestCall variable(String name, Object... values) {
        return (MockedApache5RestCall) super.variable(name, values);
    }

    @Override
    public MockedApache5RestCall parameter(String name, Object... values) {
        return (MockedApache5RestCall) super.parameter(name, values);
    }

    @Override
    public MockedApache5RestCall parameters(String name, Collection<?> values) {
        return (MockedApache5RestCall) super.parameters(name, values);
    }

    @Override
    public MockedApache5RestCall parameters(Collection<? extends Pair<String, ?>> values) {
        return (MockedApache5RestCall) super.parameters(values);
    }

    @Override
    public MockedApache5RestCall formatter(RestFormatter formatter) {
        return (MockedApache5RestCall) super.formatter(formatter);
    }

    @Override
    public MockedApache5RestCall contentType(MediaType contentType) {
        return (MockedApache5RestCall) super.contentType(contentType);
    }

    @Override
    public MockedApache5RestCall body(Object body) {
        return (MockedApache5RestCall) super.body(body);
    }

    @Override
    public <T> MockedApache5RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException {
        return (MockedApache5RestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    public <T> MockedApache5RestResponse<T> invoke(RestMethod method, GenericType<T> responseType)
        throws RestException {
        return (MockedApache5RestResponse<T>) super.invoke(method, responseType);
    }

    @Override
    protected <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType, HttpUriRequestBase request)
        throws RestException, RestRequestException {
        return new MockedApache5RestResponse<>(responseType, request);
    }
}
