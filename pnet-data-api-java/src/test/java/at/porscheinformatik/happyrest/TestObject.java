package at.porscheinformatik.happyrest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

public class TestObject {

    private static final JsonMapper JSON_MAPPER = new JsonMapper();

    public static TestObject of(String key, String value) {
        TestObject result = new TestObject(key);

        result.setValue(value);

        return result;
    }

    private final String key;

    private String value;

    @JsonCreator
    public TestObject(@JsonProperty("key") String key) {
        super();
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    @JsonIgnore
    public String toJsonString() {
        try {
            return JSON_MAPPER.writeValueAsString(this);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to write JSON", e);
        }
    }

    @Override
    public String toString() {
        return String.format("TestObject [key=%s, value=%s]", key, value);
    }
}
