package pnet.data.api.util;

/**
 * Restricts whether the person is an internal employee or not
 *
 * @param <SELF> the type of the restrict for chaining
 * @author scar
 */
public interface RestrictInternal<SELF extends Restrict<SELF>> extends Restrict<SELF>
{
    default SELF internal(boolean internal)
    {
        return restrict("internal", internal);
    }
}
