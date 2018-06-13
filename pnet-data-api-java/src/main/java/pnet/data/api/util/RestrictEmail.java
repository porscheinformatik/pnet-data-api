package pnet.data.api.util;

/**
 * Restricts emails.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictEmail<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF email(String... emails)
    {
        return restrict("email", (Object[]) emails);
    }

}
