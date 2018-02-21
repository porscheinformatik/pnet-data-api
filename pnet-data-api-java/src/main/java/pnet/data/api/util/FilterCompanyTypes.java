package pnet.data.api.util;

/**
 * Filters company types.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface FilterCompanyTypes<SELF extends Filter<SELF>> extends Filter<SELF>
{

    default SELF filterCompanyType(String... companyTypeMatchcodes)
    {
        return filter("companyType", (Object[]) companyTypeMatchcodes);
    }

}
