package pnet.data.api.util;

import java.util.Collection;

/**
 * Restricts controlling areas.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictControllingArea<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF controllingArea(String... controllingAreas) {
        return restrict("controllingArea", (Object[]) controllingAreas);
    }

    default SELF controllingAreas(Collection<String> controllingAreas) {
        return controllingArea(Restrict.toArray(controllingAreas, new String[0]));
    }
}
