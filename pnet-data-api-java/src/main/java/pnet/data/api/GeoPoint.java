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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A point on the globe.
 *
 * @author ham
 */
public class GeoPoint implements Serializable
{

    private static final long serialVersionUID = 6055660640364446760L;
    
    private final double lat;
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

    @Override
    public String toString()
    {
        return String.format(String.format("(%.6f, %.6f)", lat, lon));
    }

}
