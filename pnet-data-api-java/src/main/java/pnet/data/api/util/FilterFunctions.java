package pnet.data.api.util;

/**
 * Filters functions
 *
 * @author ham
 * @param <T> the type of the filter for chaining
 */
public interface FilterFunctions<T extends Filter<T>> extends Filter<T>
{

    default T filterFunction(String... functionMatchcodes)
    {
        return filter("function", (Object[]) functionMatchcodes);
    }

}
