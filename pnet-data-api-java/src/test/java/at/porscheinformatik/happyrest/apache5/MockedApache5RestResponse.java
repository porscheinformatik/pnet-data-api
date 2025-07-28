package at.porscheinformatik.happyrest.apache5;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MockedRestResponse;
import at.porscheinformatik.happyrest.RestUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.message.BasicHttpRequest;

public class MockedApache5RestResponse<T> extends MockedRestResponse<T> {

    private final GenericType<T> responseType;
    private final HttpUriRequestBase request;
    private final String requestBody;

    public MockedApache5RestResponse(GenericType<T> responseType, HttpUriRequestBase request) {
        super();
        this.responseType = responseType;
        this.request = request;

        try {
            HttpEntity entity = request.getEntity();

            requestBody = entity != null
                ? new String(RestUtils.readAllBytes(entity.getContent()), StandardCharsets.UTF_8)
                : null;
        } catch (UnsupportedOperationException | IOException e) {
            throw new RuntimeException(e);
        }

        ((BasicHttpRequest) request).setAbsoluteRequestUri(true);
    }

    public HttpUriRequestBase getRequest() {
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
        return request.getRequestUri();
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
