package pnet.data.api.util;

/**
 * Filters activities
 *
 * @author ham
 * @param <T> the type of the filter for chaining
 */
public interface FilterActivities<T extends Filter<T>> extends Filter<T>
{

    default T filterActivity(String... activityMatchcodes)
    {
        return filter("activity", (Object[]) activityMatchcodes);
    }

}
