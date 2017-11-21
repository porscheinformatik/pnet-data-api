package pnet.data.api.companytype;

import java.util.Collection;
import java.util.stream.Collectors;

import pnet.data.api.tenant.Tenant;
import pnet.data.api.tenant.WithTenants;

/**
 * Extracts tenant information from company types.
 *
 * @author ham
 */
public interface WithTenantsAndCompanyTypeLinks extends WithTenants, WithCompanyTypeLinks
{

    @Override
    default Collection<Tenant> getTenants()
    {
        return getCompanyTypes().stream().map(prerequisite -> prerequisite.getTenant()).collect(Collectors.toSet());
    }

}
