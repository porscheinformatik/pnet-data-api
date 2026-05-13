package pnet.data.api.person;

public enum PersonHierarchyType {
    RESPONSIBLE,
    RECERTIFIER,
    /**
     * Used for deserialization of unknown enum values from JSON (see UnknownEnumDeserializationHandler).
     */
    UNKNOWN,
}
