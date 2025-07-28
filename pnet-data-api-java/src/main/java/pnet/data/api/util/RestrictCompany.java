package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts company matchcodes.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictCompany<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF company(String... companyMatchcodes) {
        return restrict("company", (Object[]) companyMatchcodes);
    }

    default SELF companies(Collection<String> companies) {
        return company(companies.toArray(new String[0]));
    }
}
