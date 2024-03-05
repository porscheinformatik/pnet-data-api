package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts leading company matchcodes.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictLeadingCompany<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF leadingCompany(String... companyMatchcodes)
    {
        return restrict("leadingCompany", (Object[]) companyMatchcodes);
    }

    default SELF leadingCompanies(Collection<String> companies)
    {
        return leadingCompany(companies.toArray(new String[0]));
    }

}
