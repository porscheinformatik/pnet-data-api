package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts leading company numbers.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictLeadingCompanyNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF leadingCompanyNumber(String... companyNumbers)
    {
        return restrict("leadingCompanyNumber", (Object[]) companyNumbers);
    }

    default SELF leadingCompanyNumbers(Collection<String> companyNumbers)
    {
        return leadingCompanyNumber(companyNumbers.toArray(new String[0]));
    }

}
