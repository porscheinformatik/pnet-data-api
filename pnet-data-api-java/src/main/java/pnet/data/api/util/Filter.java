package pnet.data.api.util;

/**
 * A filter for Data-API queries
 *
 * @author ham
 * @param <T> the type of the filter, for chaining
 */
@FunctionalInterface
public interface Filter<T extends Filter<T>>
{

    T filter(String key, Object... values);

}
