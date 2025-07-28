package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

class LocalDateDeserializerTest {

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");
    private static final ZoneId SGT = ZoneId.of("UTC-6");

    @Test
    void testNull() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(CET))
        );

        LocalDate result = objectMapper.readValue("null", LocalDate.class);

        assertThat(result, nullValue());
    }

    @Test
    void testDateUtc() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(UTC))
        );

        LocalDate result = objectMapper.readValue("\"2000-01-01\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testDateCet() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(CET))
        );

        LocalDate result = objectMapper.readValue("\"2000-01-01\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testDateTimeUtc() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(UTC))
        );

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testDateTimeCet() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(CET))
        );

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testZonedDateTimeUtc() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(UTC))
        );

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testZonedDateTimeCet() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(CET))
        );

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
    }

    @Test
    void testZonedDateTimeSgt() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(SGT))
        );

        LocalDate result = objectMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDate.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(1999));
        assertThat(result.getMonth(), is(Month.DECEMBER));
        assertThat(result.getDayOfMonth(), is(31));
    }
}
