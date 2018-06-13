package pnet.data.api.util;

/**
 * Restricts preferred user ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictPreferredUserId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF preferredUserId(String... preferredUserIds)
    {
        return restrict("preferredUserId", (Object[]) preferredUserIds);
    }

}
