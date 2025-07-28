package pnet.data.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pnet.data.api.util.SearchAfterDeserializer;
import pnet.data.api.util.SearchAfterSerializer;

@JsonSerialize(using = SearchAfterSerializer.class)
@JsonDeserialize(using = SearchAfterDeserializer.class)
public class SearchAfter {

    public static final SearchAfter EMPTY = new SearchAfter(null);

    public static SearchAfter of(String value) {
        return new SearchAfter(value);
    }

    private final String value;

    protected SearchAfter(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
