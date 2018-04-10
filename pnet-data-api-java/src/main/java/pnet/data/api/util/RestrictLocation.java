package pnet.data.api.util;

import pnet.data.api.GeoDistance;

/**
 * Restricts location.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictLocation<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF location(GeoDistance... locations)
    {
        return restrict("location", (Object[]) locations);
    }

}
