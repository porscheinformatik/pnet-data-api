package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts contract types.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictContractType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF contractType(String... contractTypeMatchcodes)
    {
        return restrict("contractType", (Object[]) contractTypeMatchcodes);
    }

    default SELF contractTypes(Collection<String> contractTypeMatchcodes)
    {
        return contractType(contractTypeMatchcodes.toArray(new String[contractTypeMatchcodes.size()]));
    }

}
