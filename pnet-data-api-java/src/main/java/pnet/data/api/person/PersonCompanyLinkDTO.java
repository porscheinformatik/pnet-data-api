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
package pnet.data.api.person;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds one employment of a person.
 *
 * @author ham
 */
@ApiModel(description = "Holds minimal information about a employment the person has/had.")
public class PersonCompanyLinkDTO implements WithValidPeriod, Serializable
{

    private static final long serialVersionUID = 7199829304360405636L;

    @ApiModelProperty(notes = "The unique id of the company the person has/had an employment at.")
    private final Integer companyId;
    
    @ApiModelProperty(notes = "The matchcode of the company the person has/had an employment at.")
    private final String companyMatchcode;
    
    @ApiModelProperty(notes = "The number of the company the person has/had an employment at.")
    private final String companyNumber;
    
    @ApiModelProperty(notes = "The date and time from when this person has/had an employment at the company.")
    private final LocalDateTime validFrom;
    
    @ApiModelProperty(notes = "The date and time till when this brand has/had an employment at the company.")
    private final LocalDateTime validTo;
    

    public PersonCompanyLinkDTO(@JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode, @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo)
    {
        super();

        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public String getCompanyMatchcode()
    {
        return companyMatchcode;
    }

    public String getCompanyNumber()
    {
        return companyNumber;
    }

    @Override
    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    @Override
    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
        result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());

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

        if (getClass() != obj.getClass())
        {
            return false;
        }

        PersonCompanyLinkDTO other = (PersonCompanyLinkDTO) obj;

        if (companyId == null)
        {
            if (other.companyId != null)
            {
                return false;
            }
        }
        else if (!companyId.equals(other.companyId))
        {
            return false;
        }

        if (validFrom == null)
        {
            if (other.validFrom != null)
            {
                return false;
            }
        }
        else if (!validFrom.equals(other.validFrom))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "PersonCompanyLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, validFrom=%s, validTo=%s]",
                companyId, companyMatchcode, companyNumber, validFrom, validTo);
    }

}
