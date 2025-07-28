package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts ids.
 *
 * @param <IdT> the type of id
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictId<IdT, SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF id(@SuppressWarnings("unchecked") IdT... ids) {
        return restrict("id", (Object[]) ids);
    }

    default SELF ids(Collection<IdT> ids) {
        return restrict("id", ids.toArray(new Object[0]));
    }
}
