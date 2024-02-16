package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class LocalDateTimeDeserializerTest
{

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");

    @Test
    public void testNull() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(
                new SimpleModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(UTC)));

        LocalDateTime result = objectMapper.readValue("null", LocalDateTime.class);

        assertThat(result, nullValue());
    }

    @Test
    public void testDateUtc() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(
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
    public void testDateCet() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(
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
    public void testDateTimeUtc() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(
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
    public void testDateTimeCet() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(
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
    public void testZonedDateTimeUtc() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(
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
    public void testZonedDateTimeCet() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(
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
