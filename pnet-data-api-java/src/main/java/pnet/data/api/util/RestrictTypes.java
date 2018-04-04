package pnet.data.api.util;

/**
 * Restricts types.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictTypes<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF type(String... types)
    {
        return restrict("type", (Object[]) types);
    }

}
