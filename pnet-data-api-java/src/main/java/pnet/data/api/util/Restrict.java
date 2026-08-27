package pnet.data.api.util;

import java.util.Collection;

/**
 * A filter for Data-API queries
 *
 * @param <SELF> the type of the filter, for chaining
 * @author ham
 */
@FunctionalInterface
public interface Restrict<SELF extends Restrict<SELF>> {
    SELF restrict(String parameterName, Object... values);

    static <T> T[] toArray(Collection<T> values, T[] emptyArray) {
        return values == null ? emptyArray : values.toArray(emptyArray);
    }

    static Object[] toArray(Collection<?> values) {
        return values == null ? new Object[0] : values.toArray();
    }
}

