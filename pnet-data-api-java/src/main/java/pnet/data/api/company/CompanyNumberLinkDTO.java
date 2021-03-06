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

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds an additional company number.
 *
 * @author ham
 */
@ApiModel(
    description = "Holds minimal information about an additional company number of the company. The matchode fits "
        + "the matchcodes of the company number type interface.")
public class CompanyNumberLinkDTO implements WithMatchcode, Serializable
{

    private static final long serialVersionUID = 2495670532285085314L;

    @ApiModelProperty(notes = "The matchcode of the additional company number (fits the matchcodes of the company "
        + "number type interface.")
    private final String matchcode;

    @ApiModelProperty(notes = "The actual additional company number.")
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());
        return result;
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
        if (!(obj instanceof CompanyNumberLinkDTO))
        {
            return false;
        }
        CompanyNumberLinkDTO other = (CompanyNumberLinkDTO) obj;
        if (matchcode == null)
        {
            if (other.matchcode != null)
            {
                return false;
            }
        }
        else if (!matchcode.equals(other.matchcode))
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
