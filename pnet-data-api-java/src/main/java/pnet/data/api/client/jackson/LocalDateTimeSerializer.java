package pnet.data.api.client.jackson;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

/**
 * @author Daniel Furtlehner
 * @author Manfred Hantschel
 */
public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    private final ZoneId zoneId;

    public LocalDateTimeSerializer() {
        this(ZoneId.systemDefault());
    }

    public LocalDateTimeSerializer(ZoneId zoneId) {
        super(LocalDateTime.class);
        this.zoneId = zoneId;
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializationContext provider)
        throws JacksonException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeString(value.atZone(zoneId).format(DateTimeFormatter.ISO_INSTANT));
        }
    }
}
