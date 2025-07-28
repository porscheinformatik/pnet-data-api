package pnet.data.api.numbertype;

import java.util.Collection;
import java.util.Objects;

/**
 * A data object, that contains {@link NumberTypeLinkDTO}s.
 *
 * @author ham
 */
public interface WithNumberTypeLinks {
    /**
     * Returns all {@link NumberTypeLinkDTO}.
     *
     * @return a collection, never null
     */
    Collection<NumberTypeLinkDTO> getNumberTypes();

    /**
     * Creates a collection of {@link NumberTypeLinkDTO}s only containing items with the specified tenant.
     *
     * @param tenant the tenant
     * @return a collection, never null
     */
    default Collection<NumberTypeLinkDTO> getNumberTypesOfTenant(String tenant) {
        return getNumberTypes()
            .stream()
            .filter(nt -> Objects.equals(tenant, nt.getTenant()))
            .toList();
    }
}
