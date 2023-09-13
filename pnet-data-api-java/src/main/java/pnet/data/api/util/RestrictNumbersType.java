package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts the type of the assigned numbers of a person.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
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
        return numbersType(numbersTypeMatchcodes.toArray(new String[numbersTypeMatchcodes.size()]));
    }

}
