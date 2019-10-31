package pnet.data.api.company;

/**
 * When loading company data, companies sharing a group may be merged into the head company.
 *
 * @author HAM
 */
public enum CompanyMerge
{

    NONE,

    @Deprecated
    INTERET_GROUP,

    INTERNET_GROUP,
}
