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

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithBrandMatchcode;
import pnet.data.api.util.WithContractTypeMatchcode;
import pnet.data.api.util.WithValidPeriod;

/**
 * A contract state
 *
 * @author ham
 */
@Schema(description = "Holds minimal information about the contract state of a company. The matchcode fits to the "
    + "matchcodes of the contract state interface.")
public class CompanyContractStateLinkDTO extends AbstractLinkDTO
    implements WithBrandMatchcode, WithContractTypeMatchcode, WithValidPeriod
{
    @Serial
    private static final long serialVersionUID = 8013176883992921779L;

    @Schema(description = "The matchcode of the brand this contract state is assigned to.")
    private final String brandMatchcode;

    @Schema(description = "The matchcode of the contract type this contract state is assigned to.")
    private final String contractTypeMatchcode;

    @Schema(description = "The date and time from when this contract state is/was valid for the company. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validFrom;

    @Schema(description = "The date and time till when this contract state is/was valid for the company. "
        + "See https://github.com/porscheinformatik/pnet-data-api#validfromvalidto for additional information.")
    private final LocalDateTime validTo;

    public CompanyContractStateLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode, @JsonProperty("brand") String brandMatchcode,
        @JsonProperty("contractType") String contractTypeMatchcode, @JsonProperty("validFrom") LocalDateTime validFrom,
        @JsonProperty("validTo") LocalDateTime validTo)
    {
        super(tenant, matchcode);

        this.brandMatchcode = brandMatchcode;
        this.contractTypeMatchcode = contractTypeMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Schema(description = "The tenant (Portal-ID) where this contract state is valid.")
    @Override
    public String getTenant()
    {
        return super.getTenant();
    }

    @Schema(description = "The matchcode of the contract state (fits the matchcodes of the contract state interface)")
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
    public String getContractTypeMatchcode()
    {
        return contractTypeMatchcode;
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
        int result = super.hashCode();
        result = prime * result + ((brandMatchcode == null) ? 0 : brandMatchcode.hashCode());
        result = prime * result + ((contractTypeMatchcode == null) ? 0 : contractTypeMatchcode.hashCode());
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
        if (!(obj instanceof CompanyContractStateLinkDTO other))
        {
            return false;
        }
        if (!Objects.equals(brandMatchcode, other.brandMatchcode))
        {
            return false;
        }
        if (!Objects.equals(contractTypeMatchcode, other.contractTypeMatchcode))
        {
            return false;
        }
        return PnetDataApiUtils.equals(validFrom, other.validFrom);
    }

    @Override
    public String toString()
    {
        return String.format("%s(%s) [brandMathcode=%s, contractTypeMatchcode=%s, validFrom=%s, validTo=%s]",
            getMatchcode(), getTenant(), brandMatchcode, contractTypeMatchcode, validFrom, validTo);
    }

}
