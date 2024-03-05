package pnet.data.api.util;

/**
 * Restricts whether the user must be approved, or not
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface RestrictApproved<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF approved(boolean approved)
    {
        return restrict("approved", approved);
    }

}
