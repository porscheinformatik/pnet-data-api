package pnet.data.api.util;

import pnet.data.api.client.jackson.JacksonPnetDataApiModule;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * A pretty printer using an object mapper to print JSON.
 */
public final class PrettyPrint {

    private static final JsonMapper MAPPER = JacksonPnetDataApiModule.buildJsonMapper()
        .enable(SerializationFeature.INDENT_OUTPUT)
        .build();

    private PrettyPrint() {
        super();
    }

    public static String prettyPrint(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JacksonException e) {
            throw new IllegalArgumentException("Failed to transform to JSON", e);
        }
    }
}
