package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts EGIDs.
 *
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictEgid<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF egid(String... egids) {
        return restrict("egid", (Object[]) egids);
    }

    default SELF egids(Collection<String> egids) {
        return egid(egids.toArray(new String[0]));
    }
}
