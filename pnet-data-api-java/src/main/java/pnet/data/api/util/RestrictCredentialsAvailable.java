package pnet.data.api.util;

/**
 * Restricts whether credentials are available or not
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictCredentialsAvailable<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF credentialsAvailable(boolean credentialsAvailable)
    {
        return restrict("credentialsAvailable", credentialsAvailable);
    }

}
