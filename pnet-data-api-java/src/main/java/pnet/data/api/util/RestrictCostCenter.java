package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts cost centers.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictCostCenter<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF costCenter(String... costCenters)
    {
        return restrict("costCenter", (Object[]) costCenters);
    }

    default SELF costCenters(Collection<String> costCenters)
    {
        return costCenter(costCenters.toArray(new String[costCenters.size()]));
    }

}
