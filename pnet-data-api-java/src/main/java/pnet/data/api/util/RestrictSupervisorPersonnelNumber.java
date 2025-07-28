package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts supervisor personnel numbers
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictSupervisorPersonnelNumber<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF supervisorPersonnelNumber(String... supervisorPersonnelNumbers) {
        return restrict("supervisorPersonnelNumber", (Object[]) supervisorPersonnelNumbers);
    }

    default SELF supervisorPersonnelNumbers(Collection<String> supervisorPersonnelNumbers) {
        return supervisorPersonnelNumber(supervisorPersonnelNumbers.toArray(new String[0]));
    }
}
