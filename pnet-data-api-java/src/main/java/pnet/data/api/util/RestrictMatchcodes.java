package pnet.data.api.util;

/**
 * Restricts matchcodes.
 *
 * @author ham
 * @param <SELF> the type of the restrict for chaining
 */
public interface RestrictMatchcodes<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF restrictMatchcode(String... matchcode)
    {
        return restrict("mc", (Object[]) matchcode);
    }

}
