package pnet.data.api.client.jackson;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import pnet.data.api.util.PnetDataApiUtils;

/**
 * @author Daniel Furtlehner
 */
public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime>
{

    private static final long serialVersionUID = -7933082923583193689L;

    /**
     *
     */
    public LocalDateTimeSerializer()
    {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException
    {
        if (value != null)
        {
            jgen.writeString(PnetDataApiUtils.formatISO(value));
        }
    }
}
