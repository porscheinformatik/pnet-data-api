package pnet.data.api.util;

/**
 * The type of image
 *
 * @author HAM
 */
public enum ImageType {
    THUMBNAIL,
    ORIGINAL,
    /**
     * Used for deserialization of unknown enum values from JSON (see UnknownEnumDeserializationHandler).
     */
    UNKNOWN,
}
