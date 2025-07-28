package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts ZIPs.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictPostalCode<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF postalCode(String... postalCodes) {
        return restrict("postalCode", (Object[]) postalCodes);
    }

    default SELF postalCodes(Collection<String> postalCodes) {
        return postalCode(postalCodes.toArray(new String[0]));
    }
}
