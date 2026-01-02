package pnet.data.api.client.jackson;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

/**
 * @author Daniel Furtlehner
 * @author Manfred Hantschel
 */
public class ZonedDateTimeSerializer extends StdSerializer<ZonedDateTime> {

    public ZonedDateTimeSerializer() {
        super(ZonedDateTime.class);
    }

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator gen, SerializationContext provider)
        throws JacksonException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeString(value.format(DateTimeFormatter.ISO_INSTANT));
        }
    }
}
