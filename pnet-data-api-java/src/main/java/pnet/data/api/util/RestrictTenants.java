package pnet.data.api.util;

/**
 * Restricts tenants
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictTenants<SELF extends Restrict<SELF>> extends Restrict<SELF>
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

}
