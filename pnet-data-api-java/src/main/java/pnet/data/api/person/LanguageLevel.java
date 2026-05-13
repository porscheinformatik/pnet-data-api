package pnet.data.api.person;

/**
 * The competence levels according to the common European reference levels.
 *
 * @author HAM
 */
public enum LanguageLevel {
    UNDEFINED,

    A1,
    A2,

    B1,
    B2,

    C1,
    C2,

    /**
     * Used for deserialization of unknown enum values from JSON (see UnknownEnumDeserializationHandler).
     */
    UNKNOWN,
}
