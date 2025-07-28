package at.porscheinformatik.happyrest.java;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MockedRestResponse;
import java.net.http.HttpRequest;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MockedJavaRestResponse<T> extends MockedRestResponse<T> implements Subscriber<ByteBuffer> {

    private final HttpRequest request;
    private final GenericType<T> responseType;
    private final StringBuilder body = new StringBuilder();

    public MockedJavaRestResponse(GenericType<T> responseType, HttpRequest request) {
        super();
        this.request = request;
        this.responseType = responseType;

        request.bodyPublisher().ifPresent(publisher -> publisher.subscribe(this));
    }

    public HttpRequest getRequest() {
        return request;
    }

    public GenericType<T> getResponseType() {
        return responseType;
    }

    @Override
    public String getRequestMethod() {
        return request.method();
    }

    @Override
    public String getRequestUrl() {
        return request.uri().toString();
    }

    @Override
    public List<String> getRequestHeader(String key) {
        return request.headers().allValues(key);
    }

    @Override
    public String getRequestBody() {
        return body.toString();
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(1);
    }

    @Override
    public void onNext(ByteBuffer item) {
        byte[] bytes = new byte[item.remaining()];

        item.get(bytes);

        body.append(new String(bytes));
    }

    @Override
    public void onError(Throwable throwable) {}

    @Override
    public void onComplete() {}
}
