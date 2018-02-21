package pnet.data.api.util;

/**
 * Filters tenants
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface FilterTenants<SELF extends Filter<SELF>> extends Filter<SELF>
{

    // TODO "*" for all tenants
    //    default SELF allTenants()
    //    {
    //        return filter("t", "*");
    //    }

    default SELF filterTenant(String... tenants)
    {
        return filter("t", (Object[]) tenants);
    }

}
