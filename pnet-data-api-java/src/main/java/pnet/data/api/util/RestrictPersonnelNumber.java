package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts personnel numbers
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictPersonnelNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF personnelNumber(String... personnelNumbers)
    {
        return restrict("personnelNumber", (Object[]) personnelNumbers);
    }

    default SELF personnelNumbers(Collection<String> personnelNumbers)
    {
        return personnelNumber(personnelNumbers.toArray(new String[0]));
    }

}
