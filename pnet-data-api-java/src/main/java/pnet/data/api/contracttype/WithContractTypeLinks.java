package pnet.data.api.contracttype;

import java.util.Collection;
import java.util.Objects;

/**
 * A data object, that contains {@link ContractTypeLinkDTO}s.
 *
 * @author ham
 */
public interface WithContractTypeLinks
{

    /**
     * Returns all {@link ContractTypeLinkDTO}.
     *
     * @return a collection, never null
     */
    Collection<ContractTypeLinkDTO> getContractTypes();

    /**
     * Creates a collection of {@link ContractTypeLinkDTO}s only containing items with the specified tenant.
     *
     * @param tenant the tenant
     * @return a collection, never null
     */
    default Collection<ContractTypeLinkDTO> getContractTypesOfTenant(String tenant)
    {
        return getContractTypes().stream().filter(ct -> Objects.equals(tenant, ct.getTenant())).toList();
    }

}
