package pnet.data.api.util;

/**
 * A filter for Data-API queries
 *
 * @author ham
 * @param <T> the type of the filter, for chaining
 */
@FunctionalInterface
public interface Restrict<T extends Restrict<T>>
{

    T restrict(String key, Object... values);

}
