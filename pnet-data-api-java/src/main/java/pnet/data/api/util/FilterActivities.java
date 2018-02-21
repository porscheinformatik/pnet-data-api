package pnet.data.api.util;

/**
 * Filters activities
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface FilterActivities<SELF extends Filter<SELF>> extends Filter<SELF>
{

    default SELF filterActivity(String... activityMatchcodes)
    {
        return filter("activity", (Object[]) activityMatchcodes);
    }

}
