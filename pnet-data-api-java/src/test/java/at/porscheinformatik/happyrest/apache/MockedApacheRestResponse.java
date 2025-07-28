package at.porscheinformatik.happyrest.apache;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MockedRestResponse;
import at.porscheinformatik.happyrest.RestUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;

public class MockedApacheRestResponse<T> extends MockedRestResponse<T> {

    private final GenericType<T> responseType;
    private final HttpRequestBase request;
    private final String requestBody;

    public MockedApacheRestResponse(GenericType<T> responseType, HttpRequestBase request) {
        super();
        this.responseType = responseType;
        this.request = request;

        try {
            if (request instanceof HttpEntityEnclosingRequestBase) {
                requestBody = new String(
                    RestUtils.readAllBytes(((HttpEntityEnclosingRequestBase) request).getEntity().getContent()),
                    StandardCharsets.UTF_8
                );
            } else {
                requestBody = null;
            }
        } catch (UnsupportedOperationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpRequestBase getRequest() {
        return request;
    }

    public GenericType<T> getResponseType() {
        return responseType;
    }

    @Override
    public String getRequestMethod() {
        return request.getMethod();
    }

    @Override
    public String getRequestUrl() {
        return request.getURI().toString();
    }

    @Override
    public List<String> getRequestHeader(String key) {
        Header[] headers = request.getHeaders(key);

        if (headers == null || headers.length == 0) {
            return Collections.emptyList();
        }

        return Arrays.stream(headers).map(Header::getValue).toList();
    }

    @Override
    public String getRequestBody() {
        return requestBody;
    }
}
