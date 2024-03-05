package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts contract states.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictContractState<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF contractState(String... contractStateMatchcodes)
    {
        return restrict("contractState", (Object[]) contractStateMatchcodes);
    }

    default SELF contractStates(Collection<String> contractStateMatchcodes)
    {
        return contractState(contractStateMatchcodes.toArray(new String[0]));
    }

}
