package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class LocalDateTimeSerializerTest
{

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");

    @Test
    public void testNull() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new SimpleModule().addSerializer(new LocalDateTimeSerializer(UTC)));

        String json = objectMapper.writeValueAsString((LocalDateTime) null);

        assertThat(json, equalTo("null"));
    }

    @Test
    public void testUtc() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new SimpleModule().addSerializer(new LocalDateTimeSerializer(UTC)));

        String json = objectMapper.writeValueAsString(LocalDateTime.of(2000, 01, 01, 12, 00));

        assertThat(json, equalTo("\"2000-01-01T12:00:00Z\""));
    }

    @Test
    public void testCet() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new SimpleModule().addSerializer(new LocalDateTimeSerializer(CET)));

        String json = objectMapper.writeValueAsString(LocalDateTime.of(2000, 01, 01, 12, 00));

        assertThat(json, equalTo("\"2000-01-01T11:00:00Z\""));
    }
}
