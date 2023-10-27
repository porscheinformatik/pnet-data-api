package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.util.Collection;

/**
 * Restricts tenants
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictTenant<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default SELF tenant(String... tenants)
    {
        return restrict(TENANT_KEY, (Object[]) tenants);
    }

    default SELF tenants(Collection<String> tenants)
    {
        return tenant(tenants.toArray(new String[tenants.size()]));
    }
}
