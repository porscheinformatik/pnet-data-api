package at.porscheinformatik.happyrest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TestObject {

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
            return RestUtilsTest.OBJECT_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write JSON", e);
        }
    }

    @Override
    public String toString() {
        return String.format("TestObject [key=%s, value=%s]", key, value);
    }
}
