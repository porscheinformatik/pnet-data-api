package pnet.data.api;

import pnet.data.api.util.SearchAfterDeserializer;
import pnet.data.api.util.SearchAfterSerializer;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

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
