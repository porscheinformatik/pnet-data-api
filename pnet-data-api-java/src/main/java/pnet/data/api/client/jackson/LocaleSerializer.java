package pnet.data.api.client.jackson;

import java.util.Locale;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

/**
 * @author ham
 */
public class LocaleSerializer extends StdSerializer<Locale> {

    public LocaleSerializer() {
        super(Locale.class);
    }

    @Override
    public void serialize(Locale value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        if (value != null) {
            gen.writeString(value.toLanguageTag());
        } else {
            gen.writeNull();
        }
    }
}
