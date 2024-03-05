/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api;

import java.io.Serializable;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A point on the globe.
 *
 * @author ham
 */
@Schema(description = "Holds information about a geographical point on the earth")
public class GeoPoint implements Serializable
{

    private static final long serialVersionUID = 6055660640364446760L;

    public static GeoPoint of(double lat, double lon)
    {
        return new GeoPoint(lat, lon);
    }

    @Schema(description = "The latitude of the geographical point")
    private final double lat;

    @Schema(description = "The longitude of the geographical point")
    private final double lon;

    public GeoPoint(@JsonProperty("lat") double lat, @JsonProperty("lon") double lon)
    {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat()
    {
        return lat;
    }

    public double getLon()
    {
        return lon;
    }

    public double distanceTo(GeoPoint point)
    {
        return distance(lat, point.lat, lon, point.lon, 0, 0);
    }

    @Override
    public String toString()
    {
        return String.format(String.format(Locale.ENGLISH, "{\"lat\":%.6f,\"lon\":%.6f}", getLat(), getLon()));
    }

    /**
     * Calculate distance between two points in latitude and longitude taking into account height difference. If you are
     * not interested in height difference pass 0.0. Uses Haversine method as its base. <br/> <br/> <a href=
     * "https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
     * -what-am-i-doi">Source</a>
     *
     * @param lat1 first latitude value
     * @param lon1 first longitude value
     * @param lat2 second latitude value
     * @param lon2 second longitude value
     * @param el1 first elevation in meters
     * @param el2 second elevation in meters
     * @return the distance
     * @returns distance in meters
     */
    private static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2)
    {
        final int r = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(
            Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = r * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
