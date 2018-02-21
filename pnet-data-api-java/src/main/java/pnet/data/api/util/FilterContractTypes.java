package pnet.data.api.util;

/**
 * Filters contract types.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface FilterContractTypes<SELF extends Filter<SELF>> extends Filter<SELF>
{

    default SELF filterContractType(String... companyTypeMatchcodes)
    {
        return filter("contractType", (Object[]) companyTypeMatchcodes);
    }

}
