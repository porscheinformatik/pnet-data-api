package pnet.data.api.person;

/**
 * Used for sorting {@link PersonItemDTO}s on request. <br> Extend with further options as needed.
 */
public enum PersonOrderBy {
    RELEVANCE,

    ID,

    FIRST_NAME,

    LAST_NAME,

    /**
     * Used for deserialization of unknown enum values from JSON (see UnknownEnumDeserializationHandler).
     */
    UNKNOWN,
}
