package pnet.data.api.util;

import java.util.Collection;

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

    default SELF locations(Collection<GeoDistance> location)
    {
        return location(location.toArray(new GeoDistance[location.size()]));
    }

}
