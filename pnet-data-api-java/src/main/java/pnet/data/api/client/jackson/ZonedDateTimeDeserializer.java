package pnet.data.api.client.jackson;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Daniel Furtlehner
 * @author Manfred Hantschel
 */
public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime>
{

    private static final long serialVersionUID = 5229316963138004854L;

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

    public ZonedDateTimeDeserializer()
    {
        this(ZoneId.systemDefault());
    }

    public ZonedDateTimeDeserializer(ZoneId zoneId)
    {
        super(LocalDateTime.class);

        this.zoneId = zoneId;
    }

    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException
    {
        String dateTimeAsString = _parseString(jp, ctxt, NullsConstantProvider.nuller());

        if (dateTimeAsString == null || dateTimeAsString.length() == 0)
        {
            return null;
        }

        TemporalAccessor temporalAccessor =
            FORMATTER.parseBest(dateTimeAsString, ZonedDateTime::from, LocalDateTime::from, LocalDate::from);

        if (temporalAccessor instanceof ZonedDateTime)
        {
            return (ZonedDateTime) temporalAccessor;
        }

        if (temporalAccessor instanceof LocalDateTime)
        {
            return ((LocalDateTime) temporalAccessor).atZone(zoneId);
        }

        return ((LocalDate) temporalAccessor).atStartOfDay(zoneId);
    }
}
