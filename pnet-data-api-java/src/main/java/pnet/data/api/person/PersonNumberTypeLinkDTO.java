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
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.WithCompanyId;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds the number of a person for a company.
 *
 * @author ham
 */
@ApiModel(description = "Holds minimal information about a person")
public class PersonNumberTypeLinkDTO extends AbstractLinkDTO
    implements WithMatchcode, WithCompanyId, WithValidPeriod, Serializable
{

    private static final long serialVersionUID = -3446430282367218468L;

    @ApiModelProperty(notes = "The id of the company the person has the number at")
    private final Integer companyId;
    @ApiModelProperty(notes = "The date and time from when this person has/had an employment at the company")
    private final LocalDateTime validFrom;
    @ApiModelProperty(notes = "The date and time till when this brand has/had an employment at the company")
    private final LocalDateTime validTo;
    @ApiModelProperty(notes = "The actual number, that fits the number type")
    private final String number;

    public PersonNumberTypeLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("companyId") Integer companyId, @JsonProperty("validFrom") LocalDateTime validFrom,
        @JsonProperty("validTo") LocalDateTime validTo, @JsonProperty("number") String number)
    {
        super(tenant, matchcode);

        this.companyId = companyId;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.number = number;
    }
    
    
    @JsonPropertyDescription("A tenant where the number type is valid")
    @Override
    public String getTenant()
    {
        return super.getTenant();
    }

    @JsonPropertyDescription("The unique matchcode of the number type")
    @Override
    public String getMatchcode()
    {
        return super.getMatchcode();
    }



    @Override
    public Integer getCompanyId()
    {
        return companyId;
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

    public String getNumber()
    {
        return number;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
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
        if (!super.equals(obj))
        {
            return false;
        }
        if (!(obj instanceof PersonNumberTypeLinkDTO))
        {
            return false;
        }
        PersonNumberTypeLinkDTO other = (PersonNumberTypeLinkDTO) obj;
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
        return String.format("%s [companyId=%s, validFrom=%s, validTo=%s, number=%s]", super.toString(), companyId,
            validFrom, validTo, number);
    }

}
