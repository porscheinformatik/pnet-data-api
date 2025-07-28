package pnet.data.api.client.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * Converter module for Jackson.
 *
 * @author ham
 */
public class JacksonPnetDataApiModule extends SimpleModule {

    private static final long serialVersionUID = 460900059584008887L;

    public JacksonPnetDataApiModule(ZoneId zoneId) {
        super("pnet-data-api", new Version(1, 0, 0, null, "at.porscheinformatik.pnet", "pnet-data-api"));
        addSerializer(new ZonedDateTimeSerializer());
        addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer(zoneId));
        addSerializer(new LocalDateTimeSerializer(zoneId));
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(zoneId));
        addSerializer(new LocalDateSerializer());
        addDeserializer(LocalDate.class, new LocalDateDeserializer(zoneId));

        addSerializer(new LocaleSerializer());
        addDeserializer(Locale.class, new LocaleDeserializer());
    }

    public static ObjectMapper createObjectMapper() {
        return createObjectMapper(ZoneId.systemDefault());
    }

    public static ObjectMapper createObjectMapper(ZoneId zoneId) {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModules(new JacksonPnetDataApiModule(zoneId));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }
}
