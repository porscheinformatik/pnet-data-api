package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts SAP number
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictSapNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF sapNumber(String... numbers)
    {
        return restrict("sapNumber", (Object[]) numbers);
    }

    default SELF sapNumbers(Collection<String> numbers)
    {
        return sapNumber(numbers.toArray(new String[numbers.size()]));
    }

}
