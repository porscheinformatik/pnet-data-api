package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts number types.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictNumberType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF numberType(String... numberTypeMatchcodes)
    {
        return restrict("numberType", (Object[]) numberTypeMatchcodes);
    }

    default SELF numberTypes(Collection<String> numberTypeMatchcodes)
    {
        return numberType(numberTypeMatchcodes.toArray(new String[numberTypeMatchcodes.size()]));
    }

}
