package pnet.data.api.util;

/**
 * Restricts ZIPs.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictPostalCodes<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF postalCode(String... postalCodes)
    {
        return restrict("postalCode", (Object[]) postalCodes);
    }

}
