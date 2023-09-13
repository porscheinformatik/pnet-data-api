package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts administrative tenants
 *
 * @author scd
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictAdministrativeTenant<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF administrativeTenant(String... tenants)
    {
        return restrict("at", (Object[]) tenants);
    }

    default SELF administrativeTenants(Collection<String> tenants)
    {
        return administrativeTenant(tenants.toArray(new String[tenants.size()]));
    }

}
