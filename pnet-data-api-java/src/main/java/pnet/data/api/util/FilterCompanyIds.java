package pnet.data.api.util;

/**
 * Filters company ids
 *
 * @author ham
 * @param <T> the type of the filter for chaining
 */
public interface FilterCompanyIds<T extends Filter<T>> extends Filter<T>
{

    default T filterCompanyId(Integer... companyIds)
    {
        return filter("companyId", (Object[]) companyIds);
    }

}
