package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts company numbers.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictCompanyNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF companyNumber(String... companyNumbers)
    {
        return restrict("companyNumber", (Object[]) companyNumbers);
    }

    default SELF companyNumbers(Collection<String> companyNumbers)
    {
        return companyNumber(companyNumbers.toArray(new String[companyNumbers.size()]));
    }

}
