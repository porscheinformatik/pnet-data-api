package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts leading company ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictLeadingCompanyId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF leadingCompanyId(Integer... companyIds)
    {
        return restrict("leadingCompanyId", (Object[]) companyIds);
    }

    default SELF leadingCompanyIds(Collection<Integer> companyIds)
    {
        return leadingCompanyId(companyIds.toArray(new Integer[companyIds.size()]));
    }
}
