package pnet.data.api.util;

import java.util.Collection;

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

    default SELF personIds(Collection<Integer> personIds)
    {
        return personId(personIds.toArray(new Integer[personIds.size()]));
    }

}
