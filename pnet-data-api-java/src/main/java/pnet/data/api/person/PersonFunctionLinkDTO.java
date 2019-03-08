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
import pnet.data.api.util.WithBrandMatchcode;
import pnet.data.api.util.WithCompanyId;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds the function of a person for one company and brand.
 *
 * @author ham
 */
@ApiModel(description = "Holds minimal information about a function the person has.")
public class PersonFunctionLinkDTO extends AbstractLinkDTO
    implements WithMatchcode, WithCompanyId, WithBrandMatchcode, WithValidPeriod, Serializable
{

    private static final long serialVersionUID = -5572016715722241376L;

    @ApiModelProperty(notes = "The id of the company the person has the function at.")
    private final Integer companyId;

    @ApiModelProperty(notes = "The matchcode of the company the person has/had an function at.")
    private final String companyMatchcode;

    @ApiModelProperty(notes = "The number of the company the person has/had an function at.")
    private final String companyNumber;

    @ApiModelProperty(notes = "The matchcode of a brand the person has the function for.")
    private final String brandMatchcode;

    @ApiModelProperty(notes = "The date and time from when this person has/had an employment at the company.")
    private final LocalDateTime validFrom;

    @ApiModelProperty(notes = "The date and time till when this brand has/had an employment at the company.")
    private final LocalDateTime validTo;

    @ApiModelProperty(
        notes = "The flag that declares, whether this function is the main function of the person at the specific company or not.")
    private final boolean mainFunction;

    public PersonFunctionLinkDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("companyId") Integer companyId, @JsonProperty("companyMatchcode") String companyMatchcode,
        @JsonProperty("companyNumber") String companyNumber, @JsonProperty("brandMatchcode") String brandMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("mainFunction") boolean mainFunction)
    {
        super(tenant, matchcode);

        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.brandMatchcode = brandMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.mainFunction = mainFunction;
    }

    @JsonPropertyDescription("A tenant where the function is valid")
    @Override
    public String getTenant()
    {
        return super.getTenant();
    }

    @JsonPropertyDescription("The unique matchcode of the function")
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

    public String getCompanyMatchcode()
    {
        return companyMatchcode;
    }

    public String getCompanyNumber()
    {
        return companyNumber;
    }

    @Override
    public String getBrandMatchcode()
    {
        return brandMatchcode;
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

    public boolean isMainFunction()
    {
        return mainFunction;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((brandMatchcode == null) ? 0 : brandMatchcode.hashCode());
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
        if (!(obj instanceof PersonFunctionLinkDTO))
        {
            return false;
        }
        PersonFunctionLinkDTO other = (PersonFunctionLinkDTO) obj;
        if (brandMatchcode == null)
        {
            if (other.brandMatchcode != null)
            {
                return false;
            }
        }
        else if (!brandMatchcode.equals(other.brandMatchcode))
        {
            return false;
        }
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
                "PersonFunctionLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, brandMatchcode=%s, "
                    + "validFrom=%s, validTo=%s, mainFunction=%s]",
                companyId, companyMatchcode, companyNumber, brandMatchcode, validFrom, validTo, mainFunction);
    }

}
