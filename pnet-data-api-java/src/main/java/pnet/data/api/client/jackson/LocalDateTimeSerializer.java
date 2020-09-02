package pnet.data.api.client.jackson;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author Daniel Furtlehner
 * @author Manfred Hantschel
 */
public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime>
{

    private static final long serialVersionUID = -7933082923583193689L;

    private final ZoneId zoneId;

    public LocalDateTimeSerializer()
    {
        this(ZoneId.systemDefault());
    }

    public LocalDateTimeSerializer(ZoneId zoneId)
    {
        super(LocalDateTime.class);

        this.zoneId = zoneId;
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException
    {
        if (value == null)
        {
            jgen.writeNull();
        }
        else
        {
            jgen.writeString(value.atZone(zoneId).format(DateTimeFormatter.ISO_INSTANT));
        }
    }
}
