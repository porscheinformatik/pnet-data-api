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
package pnet.data.api.company;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an additional company number.
 *
 * @author ham
 */
@Schema(description = "Holds minimal information about an additional company number of the company. The matchode fits "
    + "the matchcodes of the company number type interface.")
public class CompanyNumberLinkDTO implements WithMatchcode, Serializable
{

    private static final long serialVersionUID = 2495670532285085314L;

    @Schema(description = "The matchcode of the additional company number (fits the matchcodes of the company "
        + "number type interface.")
    private final String matchcode;

    @Schema(description = "The actual additional company number.")
    private final String number;

    public CompanyNumberLinkDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("number") String number)
    {
        super();

        this.matchcode = matchcode;
        this.number = number;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    public String getNumber()
    {
        return number;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(matchcode);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof CompanyNumberLinkDTO other))
        {
            return false;
        }
        if (!Objects.equals(matchcode, other.matchcode))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s [number=%s]", super.toString(), number);
    }

}
