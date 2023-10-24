package pnet.data.api.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import pnet.data.api.SearchAfter;

public class SearchAfterSerializer extends StdSerializer<SearchAfter>
{
    private static final long serialVersionUID = 92805130175927527L;

    public SearchAfterSerializer()
    {
        this(null);
    }

    public SearchAfterSerializer(Class<SearchAfter> t)
    {
        super(t);
    }

    @Override
    public void serialize(SearchAfter value, JsonGenerator gen, SerializerProvider provider) throws IOException
    {
        gen.writeString(value.getValue());
    }
}
