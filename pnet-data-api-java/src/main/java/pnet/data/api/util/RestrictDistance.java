package pnet.data.api.util;

import pnet.data.api.GeoDistance;

/**
 * Restricts distance.
 *
 * @author ham
 * @param <SELF> the type of the filter for chaining
 */
public interface RestrictDistance<SELF extends Restrict<SELF>> extends Restrict<SELF>
{

    default SELF distance(GeoDistance... distances)
    {
        return restrict("distance", (Object[]) distances);
    }

}
