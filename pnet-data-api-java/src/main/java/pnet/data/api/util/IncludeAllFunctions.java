package pnet.data.api.util;

/**
 * When set, the result contains functions that are not main functions as well
 *
 * @author scar
 * @param <SELF> the type of the restrict for chaining
 */
public interface IncludeAllFunctions<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default SELF includeAllFunctions()
    {
        return restrict("includeAllFunctions", true);
    }
}
