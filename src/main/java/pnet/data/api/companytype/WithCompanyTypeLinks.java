package pnet.data.api.companytype;

import java.util.Collection;

/**
 * Used for DTOs with lists of company types.
 *
 * @author ham
 */
public interface WithCompanyTypeLinks
{

    Collection<? extends CompanyTypeLink> getCompanyTypes();

}
