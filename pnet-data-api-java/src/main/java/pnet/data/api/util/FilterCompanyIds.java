package pnet.data.api.util;

/**
 * Filters company ids
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface FilterCompanyIds<SELF extends Filter<SELF>> extends Filter<SELF>
{

    default SELF filterCompanyId(Integer... companyIds)
    {
        return filter("companyId", (Object[]) companyIds);
    }

}
