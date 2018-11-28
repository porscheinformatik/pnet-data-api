package pnet.data.api.client.jackson;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import pnet.data.api.util.PnetDataApiUtils;

/**
 * @author cet
 */
public class LocalDateSerializer extends StdSerializer<LocalDate>
{

    private static final long serialVersionUID = -7933082923583193689L;

    /**
     *
     */
    public LocalDateSerializer()
    {
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException
    {
        if (value != null)
        {
            jgen.writeString(PnetDataApiUtils.formatISODate(value));
        }
    }
}
