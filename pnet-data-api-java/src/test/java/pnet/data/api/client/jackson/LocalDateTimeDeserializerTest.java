package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

class LocalDateTimeDeserializerTest {

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");

    @Test
    void testNull() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)))
            .build();

        LocalDateTime result = jsonMapper.readValue("null", LocalDateTime.class);

        assertThat(result, nullValue());
    }

    @Test
    void testDateUtc() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)))
            .build();

        LocalDateTime result = jsonMapper.readValue("\"2000-01-01\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testDateCet() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(CET)))
            .build();

        LocalDateTime result = jsonMapper.readValue("\"2000-01-01\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testDateTimeUtc() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)))
            .build();

        LocalDateTime result = jsonMapper.readValue("\"2000-01-01T00:00:00\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testDateTimeCet() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(CET)))
            .build();

        LocalDateTime result = jsonMapper.readValue("\"2000-01-01T00:00:00\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testZonedDateTimeUtc() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)))
            .build();

        LocalDateTime result = jsonMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testZonedDateTimeCet() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(CET)))
            .build();

        LocalDateTime result = jsonMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(1));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }
}
