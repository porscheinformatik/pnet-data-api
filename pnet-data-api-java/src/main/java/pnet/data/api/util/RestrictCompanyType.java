package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts company types.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictCompanyType<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF companyType(String... companyTypeMatchcodes) {
        return restrict("companyType", (Object[]) companyTypeMatchcodes);
    }

    default SELF companyTypes(Collection<String> companyTypeMatchcodes) {
        return companyType(companyTypeMatchcodes.toArray(new String[0]));
    }
}
