package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts IBAN
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictIban<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF iban(String... numbers) {
        return restrict("iban", (Object[]) numbers);
    }

    default SELF ibans(Collection<String> numbers) {
        return iban(numbers.toArray(new String[0]));
    }
}
