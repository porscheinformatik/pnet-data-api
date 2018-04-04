package pnet.data.api.util;

/**
 * Restricts ZIPs.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictZips<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF zip(String... zips)
    {
        return restrict("zip", (Object[]) zips);
    }

}
