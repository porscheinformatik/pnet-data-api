package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts company dependent numbers
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictNumber<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF number(String... numbers) {
        return restrict("number", (Object[]) numbers);
    }

    default SELF numbers(Collection<String> numbers) {
        return number(numbers.toArray(new String[0]));
    }
}
