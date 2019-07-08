package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts functions and activities.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictRole<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    /**
     * Restrict the result to functions or activities with the specified matchcode. When using
     * {@link RestrictFunction#function(String...)} in combination with {@link RestrictActivity#activity(String...)}, a
     * user has to have both, the function and the the activity. When using this method, the user may have any, either
     * the function or the activity or both.
     *
     * @param roleMatchcodes the function and activity matchcodes
     * @return the request itself
     */
    default SELF role(String... roleMatchcodes)
    {
        return restrict("role", (Object[]) roleMatchcodes);
    }

    /**
     * Restrict the result to functions or activities with the specified matchcode. When using
     * {@link RestrictFunction#functions(Collection)} in combination with
     * {@link RestrictActivity#activities(Collection)}, a user has to have both, the function and the the activity. When
     * using this method, the user may have any, either the function or the activity or both.
     *
     * @param roleMatchcodes the function and activity matchcodes
     * @return the request itself
     */
    default SELF roles(Collection<String> roleMatchcodes)
    {
        return role(roleMatchcodes.toArray(new String[roleMatchcodes.size()]));
    }

}
