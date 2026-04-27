package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts number types.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictNumberType<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF numberType(String... numberTypeMatchcodes) {
        return restrict("numberType", (Object[]) numberTypeMatchcodes);
    }

    default SELF numberTypes(Collection<String> numberTypeMatchcodes) {
        return numberType(numberTypeMatchcodes.toArray(new String[0]));
    }
}
