package pnet.data.api.client.jackson;

import java.io.IOException;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author ham
 */
public class LocaleSerializer extends StdSerializer<Locale>
{

    private static final long serialVersionUID = -7933082923583193689L;

    public LocaleSerializer()
    {
        super(Locale.class);
    }

    @Override
    public void serialize(Locale value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException
    {
        if (value != null)
        {
            jgen.writeString(value.toLanguageTag());
        }
        else
        {
            jgen.writeNull();
        }
    }
}
