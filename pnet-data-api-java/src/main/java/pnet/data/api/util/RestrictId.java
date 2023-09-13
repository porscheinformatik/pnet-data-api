package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictId<IdT, SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF id(@SuppressWarnings("unchecked") IdT... ids)
    {
        return restrict("id", (Object[]) ids);
    }

    default SELF ids(Collection<IdT> ids)
    {
        return restrict("id", ids.toArray(new Object[ids.size()]));
    }

}
