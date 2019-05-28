package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the type of the assigned numbers of a person.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictNumbersType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF numbersType(String... numbersTypeMatchcodes)
    {
        return restrict("numbersType", (Object[]) numbersTypeMatchcodes);
    }

    default SELF numbersTypes(Collection<String> numbersTypeMatchcodes)
    {
        return numbersType(numbersTypeMatchcodes.toArray(new String[numbersTypeMatchcodes.size()]));
    }

}
