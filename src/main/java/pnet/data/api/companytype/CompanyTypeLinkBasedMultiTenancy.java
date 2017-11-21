package pnet.data.api.companytype;

import java.util.Collection;
import java.util.stream.Collectors;

import pnet.data.api.tenant.MultiTenancy;
import pnet.data.api.tenant.Tenant;

/**
 * Extracts tenant information from company types.
 *
 * @author ham
 */
public interface CompanyTypeLinkBasedMultiTenancy extends MultiTenancy
{

    Collection<? extends CompanyTypeLink> getCompanyTypes();

    @Override
    default Collection<Tenant> getTenants()
    {
        return getCompanyTypes().stream().map(prerequisite -> prerequisite.getTenant()).collect(Collectors.toSet());
    }

}
