package pnet.data.api.brand;

import java.util.Collection;
import java.util.stream.Collectors;

import pnet.data.api.tenant.Tenant;
import pnet.data.api.tenant.WithTenants;

/**
 * Provides tenants based on brands.
 *
 * @author ham
 */
public interface WithTenantsAndBrandLinks extends WithTenants, WithBrandLinks
{

    @Override
    default Collection<Tenant> getTenants()
    {
        return getBrands().stream().map(prerequisite -> prerequisite.getTenant()).collect(Collectors.toSet());
    }

}
