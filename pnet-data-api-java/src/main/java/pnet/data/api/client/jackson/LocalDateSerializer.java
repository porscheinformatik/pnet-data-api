package pnet.data.api.client.jackson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

/**
 * @author cet
 * @author Manfred Hantschel
 */
public class LocalDateSerializer extends StdSerializer<LocalDate> {

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeString(value.format(DateTimeFormatter.ISO_DATE));
        }
    }
}
