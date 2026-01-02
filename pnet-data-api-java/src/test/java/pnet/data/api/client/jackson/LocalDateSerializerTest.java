package pnet.data.api.client.jackson;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

class LocalDateSerializerTest {

    @Test
    void testNull() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addSerializer(new LocalDateSerializer()))
            .build();

        String json = jsonMapper.writeValueAsString((LocalDate) null);

        assertThat(json, equalTo("null"));
    }

    @Test
    void testUtc() {
        JsonMapper jsonMapper = JsonMapper.builder()
            .addModule(new SimpleModule().addSerializer(new LocalDateSerializer()))
            .build();

        String json = jsonMapper.writeValueAsString(LocalDate.of(2000, 01, 01));

        assertThat(json, equalTo("\"2000-01-01\""));
    }
}
