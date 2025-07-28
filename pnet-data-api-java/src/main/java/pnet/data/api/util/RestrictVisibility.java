package pnet.data.api.util;

import java.util.Collection;
import pnet.data.api.settings.Visibility;

/**
 * Restricts the visibility of function and activities.
 *
 * @param <SELF> the type of the filter for chaining
 * @author ham
 */
public interface RestrictVisibility<SELF extends Restrict<SELF>> extends Restrict<SELF> {
    default SELF visibility(Visibility... visibilities) {
        return restrict("visibility", (Object[]) visibilities);
    }

    default SELF visibilities(Collection<Visibility> visibilities) {
        return visibility(visibilities.toArray(new Visibility[0]));
    }
}
