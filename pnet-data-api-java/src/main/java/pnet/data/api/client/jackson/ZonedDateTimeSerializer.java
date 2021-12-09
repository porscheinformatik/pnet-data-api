package pnet.data.api.client.jackson;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author Daniel Furtlehner
 * @author Manfred Hantschel
 */
public class ZonedDateTimeSerializer extends StdSerializer<ZonedDateTime>
{
    private static final long serialVersionUID = 2976636654518601041L;

    public ZonedDateTimeSerializer()
    {
        super(ZonedDateTime.class);
    }

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException
    {
        if (value == null)
        {
            jgen.writeNull();
        }
        else
        {
            jgen.writeString(value.format(DateTimeFormatter.ISO_INSTANT));
        }
    }
}
