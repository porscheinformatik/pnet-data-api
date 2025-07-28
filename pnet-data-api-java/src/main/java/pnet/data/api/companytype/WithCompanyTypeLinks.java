package pnet.data.api.companytype;

import java.util.Collection;
import java.util.Objects;

/**
 * A data object, that contains {@link CompanyTypeLinkDTO}s.
 *
 * @author ham
 */
public interface WithCompanyTypeLinks {
    /**
     * Returns all {@link CompanyTypeLinkDTO}.
     *
     * @return a collection, never null
     */
    Collection<CompanyTypeLinkDTO> getCompanyTypes();

    /**
     * Creates a collection of {@link CompanyTypeLinkDTO}s only containing items with the specified tenant.
     *
     * @param tenant the tenant
     * @return a collection, never null
     */
    default Collection<CompanyTypeLinkDTO> getCompanyTypesOfTenant(String tenant) {
        return getCompanyTypes()
            .stream()
            .filter(ct -> Objects.equals(tenant, ct.getTenant()))
            .toList();
    }
}
