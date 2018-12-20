package pnet.data.api.util;

/**
 * Restricts reference ids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictReferenceId<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF referenceId(Integer... referenceIds)
    {
        return restrict("referenceId", (Object[]) referenceIds);
    }

}
