package pnet.data.api.util;

/**
 * Restricts person ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictPersonId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF personId(Integer... personIds)
    {
        return restrict("personId", (Object[]) personIds);
    }

}
