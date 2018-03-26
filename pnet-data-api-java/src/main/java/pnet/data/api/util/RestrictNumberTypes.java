package pnet.data.api.util;

/**
 * Restricts number types.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictNumberTypes<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF numberType(String... numberTypeMatchcodes)
    {
        return restrict("numberType", (Object[]) numberTypeMatchcodes);
    }

}
