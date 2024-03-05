package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts Guids.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictGuid<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF guid(String... guids)
    {
        return restrict("guid", (Object[]) guids);
    }

    default SELF guids(Collection<String> guids)
    {
        return guid(guids.toArray(new String[0]));
    }

}
