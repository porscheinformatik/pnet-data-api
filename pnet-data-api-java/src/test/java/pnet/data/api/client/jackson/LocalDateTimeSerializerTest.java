package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

class LocalDateTimeSerializerTest {

    private static final ZoneId UTC = ZoneId.of("UTC");
    private static final ZoneId CET = ZoneId.of("CET");

    @Test
    void testNull() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addSerializer(new LocalDateTimeSerializer(UTC)))
            .build();

        String json = jsonMapper.writeValueAsString((LocalDateTime) null);

        assertThat(json, equalTo("null"));
    }

    @Test
    void testUtc() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addSerializer(new LocalDateTimeSerializer(UTC)))
            .build();

        String json = jsonMapper.writeValueAsString(LocalDateTime.of(2000, 01, 01, 12, 00));

        assertThat(json, equalTo("\"2000-01-01T12:00:00Z\""));
    }

    @Test
    void testCet() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addSerializer(new LocalDateTimeSerializer(CET)))
            .build();

        String json = jsonMapper.writeValueAsString(LocalDateTime.of(2000, 01, 01, 12, 00));

        assertThat(json, equalTo("\"2000-01-01T11:00:00Z\""));
    }
}
