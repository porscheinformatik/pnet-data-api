package pnet.data.api.util;

/**
 * Restricts matchcodes.
 *
 * @author ham
 * @param <T> the type of the restrict for chaining
 */
public interface RestrictMatchcodes<T extends Restrict<T>> extends Restrict<T>
{

    default T restrictMatchcode(String... matchcode)
    {
        return restrict("mc", (Object[]) matchcode);
    }

}
