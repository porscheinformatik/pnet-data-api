package pnet.data.api.util;

import pnet.data.api.SearchAfter;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class SearchAfterSerializer extends StdSerializer<SearchAfter> {

    public SearchAfterSerializer() {
        this(SearchAfter.class);
    }

    public SearchAfterSerializer(Class<SearchAfter> t) {
        super(t);
    }

    @Override
    public void serialize(SearchAfter value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        gen.writeString(value.getValue());
    }
}
