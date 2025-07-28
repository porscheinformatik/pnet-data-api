package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts external id
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictExternalId<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF externalId(String... numbers) {
        return restrict("externalId", (Object[]) numbers);
    }

    default SELF externalIds(Collection<String> numbers) {
        return externalId(numbers.toArray(new String[0]));
    }
}
