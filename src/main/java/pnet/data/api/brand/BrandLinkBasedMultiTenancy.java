package pnet.data.api.brand;

import java.util.Collection;
import java.util.stream.Collectors;

import pnet.data.api.tenant.MultiTenancy;
import pnet.data.api.tenant.Tenant;

/**
 * A {@link PrerequisiteDTOContainer} that implements {@link MultiTenancy} based on the {@link PrerequisiteType#Brand}
 * flags.
 *
 * @author ham
 */
public interface BrandLinkBasedMultiTenancy extends MultiTenancy
{

    Collection<? extends BrandLink> getBrands();

    @Override
    default Collection<Tenant> getTenants()
    {
        return getBrands().stream().map(prerequisite -> prerequisite.getTenant()).collect(Collectors.toSet());
    }

}
