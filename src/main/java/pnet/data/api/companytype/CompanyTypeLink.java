package pnet.data.api.companytype;

import pnet.data.api.tenant.Tenant;

/**
 * A link to a company type.
 * 
 * @author ham
 */
public interface CompanyTypeLink
{

    Tenant getTenant();

    CompanyTypeMatchcode getCompanyTypeMatchcode();

}
