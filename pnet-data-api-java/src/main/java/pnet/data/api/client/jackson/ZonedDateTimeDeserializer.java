package pnet.data.api.client.jackson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.impl.NullsConstantProvider;
import tools.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Daniel Furtlehner
 * @author Manfred Hantschel
 */
public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .append(DateTimeFormatter.ISO_LOCAL_DATE)
        .optionalStart()
        .appendLiteral('T')
        .append(DateTimeFormatter.ISO_LOCAL_TIME)
        .optionalStart()
        .appendOffsetId()
        .optionalStart()
        .appendLiteral('[')
        .parseCaseSensitive()
        .appendZoneRegionId()
        .appendLiteral(']')
        .optionalEnd()
        .optionalEnd()
        .optionalEnd()
        .toFormatter();

    private final ZoneId zoneId;

    public ZonedDateTimeDeserializer() {
        this(ZoneId.systemDefault());
    }

    public ZonedDateTimeDeserializer(ZoneId zoneId) {
        super(LocalDateTime.class);
        this.zoneId = zoneId;
    }

    @Override
    public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        String dateTimeAsString = _parseString(p, ctxt, NullsConstantProvider.nuller());

        if (dateTimeAsString == null || dateTimeAsString.isEmpty()) {
            return null;
        }

        TemporalAccessor temporalAccessor = FORMATTER.parseBest(
            dateTimeAsString,
            ZonedDateTime::from,
            LocalDateTime::from,
            LocalDate::from
        );

        if (temporalAccessor instanceof ZonedDateTime zonedDateTime) {
            return zonedDateTime;
        }

        if (temporalAccessor instanceof LocalDateTime localDateTime) {
            return localDateTime.atZone(zoneId);
        }

        return ((LocalDate) temporalAccessor).atStartOfDay(zoneId);
    }
}
