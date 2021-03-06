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
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.WithBrandMatchcode;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds the contracts of a company.
 *
 * @author ham
 */
@ApiModel(description = "Holds minimal information about the contract type of a company. The matchcode fits to the "
    + "matchcodes of the contract types interface.")
public class CompanyContractTypeLinkDTO extends AbstractLinkDTO
    implements WithBrandMatchcode, WithValidPeriod, Serializable
{

    private static final long serialVersionUID = 5617472922439542723L;

    @ApiModelProperty(notes = "The matchcode of the brand this contract type is assigned to.")
    private final String brandMatchcode;

    @ApiModelProperty(notes = "The date and time from when this contract type is/was valid for the company. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validFrom;

    @ApiModelProperty(notes = "The date and time till when this contract type is/was valid for the company. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validTo;

    @ApiModelProperty(notes = "The date and time from when this contract type is/was valid to a limited extent.")
    private final LocalDateTime limitedExtentFrom;

    @ApiModelProperty(notes = "If this flag is set to true, the contract takes part in the KVPS.")
    private final boolean kvps;

    public CompanyContractTypeLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode, @JsonProperty("brand") String brandMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("limitedExtentFrom") LocalDateTime limitedExtentFrom, @JsonProperty("kvps") boolean kvps)
    {
        super(tenant, matchcode);

        this.brandMatchcode = brandMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.limitedExtentFrom = limitedExtentFrom;
        this.kvps = kvps;
    }

    @ApiModelProperty(notes = "The tenant (Portal-ID) where this contract type reference is valid.")
    @Override
    public String getTenant()
    {
        return super.getTenant();
    }

    @ApiModelProperty(
        notes = "The matchcode of the contract type (fits the matchcodes of the contract types interface)")
    @Override
    public String getMatchcode()
    {
        return super.getMatchcode();
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

    public LocalDateTime getLimitedExtentFrom()
    {
        return limitedExtentFrom;
    }

    public boolean isKvps()
    {
        return kvps;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((brandMatchcode == null) ? 0 : brandMatchcode.hashCode());
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
        if (!(obj instanceof CompanyContractTypeLinkDTO))
        {
            return false;
        }
        CompanyContractTypeLinkDTO other = (CompanyContractTypeLinkDTO) obj;
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
            .format("%s(%s) [brandMatchcode=%s, validFrom=%s, validTo=%s, kvps=%s]", getMatchcode(), getTenant(),
                brandMatchcode, validFrom, validTo, kvps);
    }

}
