package pnet.data.api.util;

/**
 * Restricts company matchcodes.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictCompany<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF company(String... companyMatchcodes)
    {
        return restrict("company", (Object[]) companyMatchcodes);
    }

}
