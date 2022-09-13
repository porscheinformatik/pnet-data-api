package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts to items, that have been assigned to person ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictAssignedToPersonId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default SELF assignedToPersonId(Integer... assignedToPersonId)
    {
        return restrict("assignedToPersonId", (Object[]) assignedToPersonId);
    }

    default SELF assignedToPersonIds(Collection<Integer> assignedToPersonIds)
    {
        return assignedToPersonId(assignedToPersonIds.toArray(new Integer[assignedToPersonIds.size()]));
    }
}
