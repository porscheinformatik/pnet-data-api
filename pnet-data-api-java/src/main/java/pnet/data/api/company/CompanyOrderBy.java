package pnet.data.api.company;

/**
 * Used for sorting {@link CompanyItemDTO}s on request. <br> Extend with further options as needed.
 */
public enum CompanyOrderBy
{
    RELEVANCE,

    ID,

    COMPANY_NUMBER,

    COMPANY_LABEL,

    /**
     * Used for deserialization of unknown enum values from JSON (see UnknownEnumDeserializationHandler).
     */
    UNKNOWN,
}
