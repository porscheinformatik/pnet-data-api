package pnet.data.api.person;

/**
 * Used for sorting persons on request. <br> Extend with further options as needed.
 *
 * @author scar
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
