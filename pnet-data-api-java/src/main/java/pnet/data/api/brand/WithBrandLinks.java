package pnet.data.api.brand;

import java.util.Collection;
import java.util.Objects;

/**
 * A data object, that contains {@link BrandLinkDTO}s.
 *
 * @author ham
 */
public interface WithBrandLinks
{

    /**
     * Returns all {@link BrandLinkDTO}.
     *
     * @return a collection, never null
     */
    Collection<BrandLinkDTO> getBrands();

    /**
     * Creates a collection of {@link BrandLinkDTO}s only containing items with the specified tenant.
     *
     * @param tenant the tenant
     * @return a collection, never null
     */
    default Collection<BrandLinkDTO> getBrandsOfTenant(String tenant)
    {
        return getBrands().stream().filter($ -> Objects.equals(tenant, $.getTenant())).toList();
    }

}
