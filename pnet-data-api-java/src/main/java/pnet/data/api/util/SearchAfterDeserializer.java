package pnet.data.api.util;

import pnet.data.api.SearchAfter;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

public class SearchAfterDeserializer extends StdDeserializer<SearchAfter> {

    public SearchAfterDeserializer() {
        this(SearchAfter.class);
    }

    public SearchAfterDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SearchAfter deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        String value = p.getValueAsString();

        return value != null ? SearchAfter.of(value) : null;
    }
}
