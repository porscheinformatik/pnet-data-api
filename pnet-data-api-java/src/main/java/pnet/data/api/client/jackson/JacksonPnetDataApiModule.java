package pnet.data.api.client.jackson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import tools.jackson.core.Version;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.json.JsonMapper.Builder;
import tools.jackson.databind.module.SimpleModule;

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

    public static JsonMapper createJsonMapper() {
        return createJsonMapper(ZoneId.systemDefault());
    }

    public static JsonMapper createJsonMapper(ZoneId zoneId) {
        return buildJsonMapper(zoneId).build();
    }

    public static Builder buildJsonMapper() {
        return buildJsonMapper(ZoneId.systemDefault());
    }

    public static Builder buildJsonMapper(ZoneId zoneId) {
        return JsonMapper.builder()
            .addModule(new JacksonPnetDataApiModule(zoneId))
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
