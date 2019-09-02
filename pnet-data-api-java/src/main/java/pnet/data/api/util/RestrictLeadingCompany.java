package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts leading company matchcodes.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictLeadingCompany<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF leadingCompany(String... companyMatchcodes)
    {
        return restrict("leadingCompany", (Object[]) companyMatchcodes);
    }

    default SELF leadingCompanies(Collection<String> companies)
    {
        return leadingCompany(companies.toArray(new String[companies.size()]));
    }

}
