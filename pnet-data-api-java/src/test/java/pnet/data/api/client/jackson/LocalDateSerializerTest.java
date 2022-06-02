package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class LocalDateSerializerTest
{
    @Test
    public void testNull() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new SimpleModule().addSerializer(new LocalDateSerializer()));

        String json = objectMapper.writeValueAsString((LocalDate) null);

        assertThat(json, equalTo("null"));
    }

    @Test
    public void testUtc() throws JsonGenerationException, IOException
    {
        ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new SimpleModule().addSerializer(new LocalDateSerializer()));

        String json = objectMapper.writeValueAsString(LocalDate.of(2000, 01, 01));

        assertThat(json, equalTo("\"2000-01-01\""));
    }
}
