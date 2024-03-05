package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts contract types.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictContractType<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF contractType(String... contractTypeMatchcodes)
    {
        return restrict("contractType", (Object[]) contractTypeMatchcodes);
    }

    default SELF contractTypes(Collection<String> contractTypeMatchcodes)
    {
        return contractType(contractTypeMatchcodes.toArray(new String[0]));
    }

}
