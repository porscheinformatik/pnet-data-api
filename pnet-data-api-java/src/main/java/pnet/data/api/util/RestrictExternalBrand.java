package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts external brands.
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface RestrictExternalBrand<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF externalBrand(String... externalBrandMatchcodes) {
        return restrict("externalBrand", (Object[]) externalBrandMatchcodes);
    }

    default SELF externalBrands(Collection<String> externalBrandMatchcodes) {
        return externalBrand(externalBrandMatchcodes.toArray(new String[0]));
    }
}
