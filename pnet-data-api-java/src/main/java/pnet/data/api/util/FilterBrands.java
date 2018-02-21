package pnet.data.api.util;

/**
 * Filters brands
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface FilterBrands<SELF extends Filter<SELF>> extends Filter<SELF>
{

    default SELF filterBrand(String... brandMatchcodes)
    {
        return filter("b", (Object[]) brandMatchcodes);
    }

}
