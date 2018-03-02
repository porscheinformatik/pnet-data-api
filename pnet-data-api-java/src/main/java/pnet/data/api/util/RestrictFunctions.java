package pnet.data.api.util;

/**
 * Restricts functions.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictFunctions<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF function(String... functionMatchcodes)
    {
        return restrict("function", (Object[]) functionMatchcodes);
    }

}
