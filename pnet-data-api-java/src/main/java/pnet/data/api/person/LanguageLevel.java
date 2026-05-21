package pnet.data.api.person;

/**
 * The competence levels according to the common European reference levels.
 *
 * @author HAM
 * @deprecated since 3.2.5
 */
@Deprecated(since = "3.2.5", forRemoval = true)
public enum LanguageLevel {
    @Deprecated(since = "3.2.5", forRemoval = true)
    UNDEFINED,

    @Deprecated(since = "3.2.5", forRemoval = true)
    A1,
    @Deprecated(since = "3.2.5", forRemoval = true)
    A2,

    @Deprecated(since = "3.2.5", forRemoval = true)
    B1,
    @Deprecated(since = "3.2.5", forRemoval = true)
    B2,

    @Deprecated(since = "3.2.5", forRemoval = true)
    C1,
    @Deprecated(since = "3.2.5", forRemoval = true)
    C2,

    /**
     * Used for deserialization of unknown enum values from JSON (see UnknownEnumDeserializationHandler).
     */
    @Deprecated(since = "3.2.5", forRemoval = true)
    UNKNOWN,
}
