package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts by VAT Id
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictVatIdNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF vatIdNumber(String... numbers)
    {
        return restrict("vatIdNumber", (Object[]) numbers);
    }

    default SELF vatIdNumbers(Collection<String> numbers)
    {
        return vatIdNumber(numbers.toArray(new String[numbers.size()]));
    }

}
