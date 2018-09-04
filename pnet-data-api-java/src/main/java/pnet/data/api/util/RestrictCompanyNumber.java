package pnet.data.api.util;

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

}
