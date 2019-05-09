package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts activities.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictActivity<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF activity(String... activityMatchcodes)
    {
        return restrict("activity", (Object[]) activityMatchcodes);
    }

    default SELF activities(Collection<String> activityMatchcodes)
    {
        return activity(activityMatchcodes.toArray(new String[activityMatchcodes.size()]));
    }

}
