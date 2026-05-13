package at.porscheinformatik.happyrest;

/**
 * The HTTP method
 *
 * @author ham
 */
public enum RestMethod {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    /**
     * Used for deserialization of unknown enum values from JSON (see UnknownEnumDeserializationHandler).
     */
    UNKNOWN,
}
