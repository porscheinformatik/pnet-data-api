package pnet.data.api.tenant;

import java.util.Collection;

/**
 * Used for DTOs that support multiple tenants.
 *
 * @author ham
 */
public interface WithTenants
{

    /**
     * @return A list of all tenants that support this item.
     */
    Collection<Tenant> getTenants();

    /**
     * Returns true if the DTOs contains the specified tenant, false otherwise.
     * 
     * @param tenant the tenant
     * @return true if present
     */
    default boolean containsTenant(Tenant tenant)
    {
        return getTenants().contains(tenant);
    }

}
