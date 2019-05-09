package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts external brands.
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictExternalBrand<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF externalBrand(String... externalBrandMatchcodes)
    {
        return restrict("externalBrand", (Object[]) externalBrandMatchcodes);
    }

    default SELF externalBrands(Collection<String> externalBrandMatchcodes)
    {
        return externalBrand(externalBrandMatchcodes.toArray(new String[externalBrandMatchcodes.size()]));
    }

}
