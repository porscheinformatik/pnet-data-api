package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts data processing register number
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictDataProcessingRegisterNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF dataProcessingRegisterNumber(String... numbers)
    {
        return restrict("dataProcessingRegisterNumber", (Object[]) numbers);
    }

    default SELF dataProcessingRegisterNumbers(Collection<String> numbers)
    {
        return dataProcessingRegisterNumber(numbers.toArray(new String[0]));
    }

}
