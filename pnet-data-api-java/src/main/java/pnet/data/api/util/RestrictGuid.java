package pnet.data.api.util;

/**
 * Restricts Guids.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictGuid<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF guid(String... guids)
    {
        return restrict("guid", (Object[]) guids);
    }

}
