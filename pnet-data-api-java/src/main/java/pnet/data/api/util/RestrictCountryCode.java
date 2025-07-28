package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts country codes.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictCountryCode<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF countryCode(String... countryCodes) {
        return restrict("countryCode", (Object[]) countryCodes);
    }

    default SELF countryCodes(Collection<String> countryCodes) {
        return countryCode(countryCodes.toArray(new String[0]));
    }
}
