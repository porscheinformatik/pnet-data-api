package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts tenants
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictTenant<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    // TODO "*" for all tenants
    //    default SELF allTenants()
    //    {
    //        return restrict("t", "*");
    //    }

    default SELF tenant(String... tenants)
    {
        return restrict("t", (Object[]) tenants);
    }

    default SELF tenants(Collection<String> tenants)
    {
        return tenant(tenants.toArray(new String[tenants.size()]));
    }

}
