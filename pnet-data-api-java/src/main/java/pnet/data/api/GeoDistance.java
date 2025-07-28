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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Locale;

/**
 * A point on the globe with a distance (in m).
 *
 * @author ham
 */
public class GeoDistance extends GeoPoint {

    private static final long serialVersionUID = 5927413577348524173L;

    public static GeoDistance of(double lat, double lon, double distance) {
        return new GeoDistance(lat, lon, distance);
    }

    private final double distance;

    public GeoDistance(
        @JsonProperty("lat") double lat,
        @JsonProperty("lon") double lon,
        @JsonProperty("distance") double distance
    ) {
        super(lat, lon);
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public boolean contains(GeoPoint point) {
        return point.distanceTo(this) <= distance;
    }

    @Override
    public String toString() {
        return String.format(
            String.format(
                Locale.ENGLISH,
                "{\"lat\":%.6f,\"lon\":%.6f,\"distance\":%.6f}",
                getLat(),
                getLon(),
                getDistance()
            )
        );
    }
}
