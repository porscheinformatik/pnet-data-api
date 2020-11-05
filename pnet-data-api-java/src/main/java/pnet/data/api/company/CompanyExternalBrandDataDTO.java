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
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithValidPeriod;

/**
 * An external brand of a company.
 *
 * @author ham
 */
@ApiModel(description = "Holds minimal information about an external brand of the company. The matchcode fits the "
    + "matchcodes of the external brand interface.")
public class CompanyExternalBrandDataDTO implements WithMatchcode, WithValidPeriod, Serializable
{

    private static final long serialVersionUID = -1049613870676274132L;

    @ApiModelProperty(
        notes = "The matchcode of the external brand (fits the matchcodes of the external brand interface.")
    private final String matchcode;

    @ApiModelProperty(notes = "The date and time from when this external brand is/was valid for the company. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validFrom;

    @ApiModelProperty(notes = "The date and time till when this external brand is/was valid for the company. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validTo;

    @ApiModelProperty(notes = "This flag declares, whether this external brand is sold by the company.")
    private final boolean sales;

    @ApiModelProperty(notes = "This flag declares, whether this external brand is serviced by the company.")
    private final boolean service;

    @ApiModelProperty(notes = "The local flag. Does local things unknown to most people, especially those writing this "
        + "documentation.")
    private final boolean local;

    public CompanyExternalBrandDataDTO(@JsonProperty("matchcode") String matchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("sales") boolean sales, @JsonProperty("service") boolean service,
        @JsonProperty("local") boolean local)
    {
        super();

        this.matchcode = matchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.sales = sales;
        this.service = service;
        this.local = local;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
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

    public boolean isSales()
    {
        return sales;
    }

    public boolean isService()
    {
        return service;
    }

    public boolean isLocal()
    {
        return local;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());
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

        CompanyExternalBrandDataDTO other = (CompanyExternalBrandDataDTO) obj;

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
            .format("%s [validFrom=%s, validTo=%s, sales=%s, service=%s, local=%s]", matchcode, validFrom, validTo,
                sales, service, local);
    }

}
