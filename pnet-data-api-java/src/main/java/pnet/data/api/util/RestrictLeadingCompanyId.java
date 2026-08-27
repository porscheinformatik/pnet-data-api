package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts leading company ids.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictLeadingCompanyId<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF leadingCompanyId(Integer... companyIds) {
        return restrict("leadingCompanyId", (Object[]) companyIds);
    }

    default SELF leadingCompanyIds(Collection<Integer> companyIds) {
        return leadingCompanyId(Restrict.toArray(companyIds, new Integer[0]));
    }
}
