package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts supervisor personnel numbers
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictSupervisorPersonnelNumber<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF supervisorPersonnelNumber(String... supervisorPersonnelNumbers)
    {
        return restrict("supervisorPersonnelNumber", (Object[]) supervisorPersonnelNumbers);
    }

    default SELF supervisorPersonnelNumbers(Collection<String> supervisorPersonnelNumbers)
    {
        return supervisorPersonnelNumber(
            supervisorPersonnelNumbers.toArray(new String[supervisorPersonnelNumbers.size()]));
    }

}
