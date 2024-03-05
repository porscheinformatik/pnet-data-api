package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts company ids.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictCompanyId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF companyId(Integer... companyIds)
    {
        return restrict("companyId", (Object[]) companyIds);
    }

    default SELF companyIds(Collection<Integer> companyIds)
    {
        return companyId(companyIds.toArray(new Integer[0]));
    }
}
