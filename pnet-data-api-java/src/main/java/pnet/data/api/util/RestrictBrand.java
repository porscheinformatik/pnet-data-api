package pnet.data.api.util;

import java.util.Arrays;
import java.util.Collection;

import pnet.data.api.PnetDataConstants;

/**
 * Restricts brands
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface RestrictBrand<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF brand(String... brandMatchcodes) {

        String[] normalizedBrandMatchcodes = Arrays.stream(brandMatchcodes)
            .map(RestrictBrand::normalizeBrandMatchcode)
            .toArray(String[]::new);

        return restrict(PnetDataConstants.BRAND_KEY, (Object[]) normalizedBrandMatchcodes);
    }

    private static String normalizeBrandMatchcode(String matchcode) {
        if (matchcode == null) {
            return PnetDataApiUtils.BRANDFREE;
        }

        if ("null".equals(matchcode)) {
            return PnetDataApiUtils.BRANDFREE;
        }

        return matchcode;
    }

    default SELF brands(Collection<String> brandMatchcodes) {
        return brand(brandMatchcodes.toArray(new String[0]));
    }

    /**
     * Can be used to find "active" entries without a specific brand, but with at least one active brand.
     *
     * @return a new instance with this restriction
     */
    default SELF anyBrand() {
        return brand(PnetDataApiUtils.WILDCARD);
    }
}
