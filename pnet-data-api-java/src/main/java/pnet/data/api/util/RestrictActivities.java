package pnet.data.api.util;

/**
 * Restricts activities.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictActivities<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF activity(String... activityMatchcodes)
    {
        return restrict("activity", (Object[]) activityMatchcodes);
    }

}
