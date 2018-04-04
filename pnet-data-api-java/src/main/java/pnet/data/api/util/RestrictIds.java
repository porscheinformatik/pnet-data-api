package pnet.data.api.util;

/**
 * Restricts ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictIds<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF id(Integer... companyIds)
    {
        return restrict("id", (Object[]) companyIds);
    }

}
