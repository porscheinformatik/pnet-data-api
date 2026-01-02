package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

class LocalDateDeserializerTest {

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");
    private static final ZoneId SGT = ZoneId.of("UTC-6");

    @Test
    void testNull() {
        ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(CET)))
            .build();

        LocalDate result = objectMapper.readValue("null", LocalDate.class);

        assertThat(result, nullValue());
    }

    @Test
    void testDateUtc() {
        ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(UTC)))
            .build();

        LocalDate result = objectMapper.readValue("\"2000-01-01\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testDateCet() {
        ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(CET)))
            .build();

        LocalDate result = objectMapper.readValue("\"2000-01-01\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testDateTimeUtc() {
        ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(UTC)))
            .build();

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testDateTimeCet() {
        ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(CET)))
            .build();

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testZonedDateTimeUtc() {
        ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(UTC)))
            .build();

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testZonedDateTimeCet() {
        ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(CET)))
            .build();

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testZonedDateTimeSgt() {
        ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(SGT)))
            .build();

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(1999));
        assertThat(result.getMonth(), is(Month.DECEMBER));
        assertThat(result.getDayOfMonth(), is(31));
    }
}
