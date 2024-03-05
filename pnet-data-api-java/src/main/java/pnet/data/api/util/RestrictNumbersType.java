package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the type of the assigned numbers of a person.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 * @deprecated use {@link RestrictNumberType} instead
 */
@Deprecated
public interface RestrictNumbersType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF numbersType(String... numbersTypeMatchcodes)
    {
        return restrict("numberType", (Object[]) numbersTypeMatchcodes);
    }

    default SELF numbersTypes(Collection<String> numbersTypeMatchcodes)
    {
        return numbersType(numbersTypeMatchcodes.toArray(new String[0]));
    }

}
