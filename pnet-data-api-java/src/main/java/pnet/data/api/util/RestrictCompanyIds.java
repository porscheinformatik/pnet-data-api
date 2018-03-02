package pnet.data.api.util;

/**
 * Restricts company ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictCompanyIds<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF companyId(Integer... companyIds)
    {
        return restrict("companyId", (Object[]) companyIds);
    }

}
