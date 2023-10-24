package pnet.data.api.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import pnet.data.api.SearchAfter;

public class SearchAfterDeserializer extends StdDeserializer<SearchAfter>
{
    private static final long serialVersionUID = -6794051706816616271L;

    public SearchAfterDeserializer()
    {
        this(null);
    }

    public SearchAfterDeserializer(Class<?> vc)
    {
        super(vc);
    }

    @Override
    public SearchAfter deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException
    {
        String value = p.getValueAsString();

        return value != null ? SearchAfter.of(value) : null;
    }
}
