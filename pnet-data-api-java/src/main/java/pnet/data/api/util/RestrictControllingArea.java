package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts controlling areas.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictControllingArea<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF controllingArea(String... controllingAreas)
    {
        return restrict("controllingArea", (Object[]) controllingAreas);
    }

    default SELF controllingAreas(Collection<String> controllingAreas)
    {
        return controllingArea(controllingAreas.toArray(new String[controllingAreas.size()]));
    }

}
