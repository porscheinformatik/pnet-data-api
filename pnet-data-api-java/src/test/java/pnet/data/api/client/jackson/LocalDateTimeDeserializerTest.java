package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

class LocalDateTimeDeserializerTest
{

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");

    @Test
    void testNull() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)));

        LocalDateTime result = objectMapper.readValue("null", LocalDateTime.class);

        assertThat(result, nullValue());
    }

    @Test
    void testDateUtc() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)));

        LocalDateTime result = objectMapper.readValue("\"2000-01-01\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testDateCet() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(CET)));

        LocalDateTime result = objectMapper.readValue("\"2000-01-01\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testDateTimeUtc() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)));

        LocalDateTime result = objectMapper.readValue("\"2000-01-01T00:00:00\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testDateTimeCet() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(CET)));

        LocalDateTime result = objectMapper.readValue("\"2000-01-01T00:00:00\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testZonedDateTimeUtc() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)));

        LocalDateTime result = objectMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(0));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }

    @Test
    void testZonedDateTimeCet() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(CET)));

        LocalDateTime result = objectMapper.readValue("\"2000-01-01T00:00:00Z\"", LocalDateTime.class);

        assertThat(result, notNullValue());
        assertThat(result.getYear(), is(2000));
        assertThat(result.getMonth(), is(Month.JANUARY));
        assertThat(result.getDayOfMonth(), is(1));
        assertThat(result.getHour(), is(1));
        assertThat(result.getMinute(), is(0));
        assertThat(result.getSecond(), is(0));
    }
}
