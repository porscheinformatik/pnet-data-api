package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.util.Collection;

/**
 * Restricts brands
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictBrand<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF brand(String... brandMatchcodes)
    {
        return restrict(BRAND_KEY, (Object[]) brandMatchcodes);
    }

    default SELF brands(Collection<String> brandMatchcodes)
    {
        return brand(brandMatchcodes.toArray(new String[brandMatchcodes.size()]));
    }

    /**
     * Can be used to find "active" entries without a specific brand, but with at least one active brand.
     *
     * @return a new instance with this restriction
     */
    default SELF anyBrand()
    {
        return brand(PnetDataApiUtils.WILDCARD);
    }

}
