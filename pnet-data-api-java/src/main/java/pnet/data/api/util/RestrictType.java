package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts types.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF type(String... typeMatchcodes)
    {
        return restrict("type", (Object[]) typeMatchcodes);
    }

    default SELF types(Collection<String> typeMatchcodes)
    {
        return type(typeMatchcodes.toArray(new String[0]));
    }

}
