package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF id(Integer... ids)
    {
        return restrict("id", (Object[]) ids);
    }

    default SELF ids(Collection<Integer> ids)
    {
        return id(ids.toArray(new Integer[ids.size()]));
    }

}
