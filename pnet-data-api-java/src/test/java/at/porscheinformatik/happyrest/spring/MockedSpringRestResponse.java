package at.porscheinformatik.happyrest.spring;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MockedRestResponse;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestUtils;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

@Deprecated(since = "2.13.x")
public class MockedSpringRestResponse<T> extends MockedRestResponse<T> {

    private static final JsonMapper JSON_MAPPER = new JsonMapper();

    private final RestMethod requestMethod;
    private final GenericType<T> responseType;
    private final URI uri;
    private final HttpEntity<Object> entity;
    private final String requestContentType;
    private final String requestBody;

    public MockedSpringRestResponse(
        RestMethod requestMethod,
        GenericType<T> responseType,
        URI uri,
        HttpEntity<Object> entity
    ) {
        super();
        this.requestMethod = requestMethod;
        this.responseType = responseType;
        this.uri = uri;
        this.entity = entity;

        requestContentType = entity.getHeaders().getFirst("Content-Type");

        // we have to fake Spring's content building
        if (requestContentType != null && requestContentType.contains("application/json")) {
            try {
                requestBody = JSON_MAPPER.writeValueAsString(entity.getBody());
            } catch (JacksonException e) {
                throw new RuntimeException("Failed to format JSON", e);
            }
        } else if (requestContentType != null && requestContentType.contains("application/x-www-form-urlencoded")) {
            @SuppressWarnings("unchecked")
            LinkedMultiValueMap<String, String> map = (LinkedMultiValueMap<String, String>) entity.getBody();

            StringBuilder builder = new StringBuilder();

            if (map != null) {
                for (Entry<String, List<String>> entries : map.entrySet()) {
                    if (!builder.isEmpty()) {
                        builder.append("&");
                    }

                    for (String value : entries.getValue()) {
                        builder
                            .append(RestUtils.encodeString(entries.getKey(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(RestUtils.encodeString(value, StandardCharsets.UTF_8));
                    }
                }
            }

            requestBody = builder.toString();
        } else {
            requestBody = String.valueOf(entity.getBody());
        }
    }

    public RestMethod getMethod() {
        return requestMethod;
    }

    public GenericType<T> getResponseType() {
        return responseType;
    }

    public URI getUri() {
        return uri;
    }

    public HttpEntity<Object> getEntity() {
        return entity;
    }

    @Override
    public String getRequestMethod() {
        return requestMethod.name();
    }

    @Override
    public String getRequestUrl() {
        return getUri().toString();
    }

    @Override
    public List<String> getRequestHeader(String key) {
        return entity.getHeaders().get(key);
    }

    @Override
    public String getRequestBody() {
        return requestBody;
    }
}
