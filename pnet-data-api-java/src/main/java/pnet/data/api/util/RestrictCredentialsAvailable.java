package pnet.data.api.util;

/**
 * Restricts whether credentials are available or not
 *
 * @param <SELF> the type of the restrict for chaining
 * @author ham
 */
public interface RestrictCredentialsAvailable<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF credentialsAvailable(boolean credentialsAvailable) {
        return restrict("credentialsAvailable", credentialsAvailable);
    }
}
