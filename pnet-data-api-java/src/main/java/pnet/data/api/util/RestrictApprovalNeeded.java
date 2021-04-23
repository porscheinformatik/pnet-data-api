package pnet.data.api.util;

/**
 * Restricts whether the function or activity needs approval.
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictApprovalNeeded<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default SELF approvalNeeded(Boolean approvalNeeded)
    {
        return restrict("approvalNeeded", approvalNeeded);
    }
}
