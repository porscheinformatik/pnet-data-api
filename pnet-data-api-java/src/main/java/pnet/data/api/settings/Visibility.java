package pnet.data.api.settings;

public enum Visibility {
    HIDDEN,

    PARTIALLY,

    VISIBLE,

    /**
     * Used for deserialization of unknown enum values from JSON (see UnknownEnumDeserializationHandler).
     */
    UNKNOWN,
}
