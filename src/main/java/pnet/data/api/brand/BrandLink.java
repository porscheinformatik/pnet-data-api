package pnet.data.api.brand;

import pnet.data.api.tenant.Tenant;

/**
 * A link to a brand.
 * 
 * @author ham
 */
public interface BrandLink
{

    Tenant getTenant();

    BrandMatchcode getBrandMatchcode();

}
