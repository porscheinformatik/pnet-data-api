package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts reference ids.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictReferenceId<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF referenceId(Integer... referenceIds) {
        return restrict("referenceId", (Object[]) referenceIds);
    }

    default SELF referenceIds(Collection<Integer> referenceIds) {
        return referenceId(referenceIds.toArray(new Integer[0]));
    }
}
