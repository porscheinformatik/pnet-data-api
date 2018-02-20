package pnet.data.api.util;

/**
 * Restricts brands
 *
 * @author ham
 * @param <T> the type of the restrict for chaining
 */
public interface RestrictBrands<T extends Restrict<T>> extends Restrict<T>
{

    default T restrictBrand(String... brandMatchcodes)
    {
        return restrict("b", (Object[]) brandMatchcodes);
    }

}
