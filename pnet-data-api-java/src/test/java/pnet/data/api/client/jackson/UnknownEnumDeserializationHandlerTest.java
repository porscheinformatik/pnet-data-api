package pnet.data.api.client.jackson;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import tools.jackson.databind.json.JsonMapper;

class UnknownEnumDeserializationHandlerTest {

    enum Color {
        RED,
        GREEN,
        BLUE,
        UNKNOWN,
    }

    enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
    }

    private final JsonMapper mapper = JacksonPnetDataApiModule.createJsonMapper();

    @Test
    void testKnownValueParsedNormally() {
        Color result = mapper.readValue("\"RED\"", Color.class);

        MatcherAssert.assertThat(result, Matchers.is(Color.RED));
    }

    @Test
    void testUnknownValueMappedToUnknown() {
        Color result = mapper.readValue("\"PURPLE\"", Color.class);

        MatcherAssert.assertThat(result, Matchers.is(Color.UNKNOWN));
    }

    @Test
    void testUnknownValueWithNoSentinelThrows() {
        Assertions.assertThrows(Exception.class, () -> mapper.readValue("\"UP\"", Direction.class));
    }
}
