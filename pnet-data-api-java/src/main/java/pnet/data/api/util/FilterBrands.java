package pnet.data.api.util;

/**
 * Filters brands
 *
 * @author ham
 * @param <T> the type of the filter for chaining
 */
public interface FilterBrands<T extends Filter<T>> extends Filter<T>
{

    default T filterBrand(String... brandMatchcodes)
    {
        return filter("b", (Object[]) brandMatchcodes);
    }

}
