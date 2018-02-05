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
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.contracttype.ContractTypeMatchcode;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds the contracts of a company.
 *
 * @author ham
 */
public class CompanyContractTypeDataDTO implements WithMatchcode<ContractTypeMatchcode>, WithValidPeriod, Serializable
{

    private static final long serialVersionUID = 5617472922439542723L;

    private final ContractTypeMatchcode matchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final boolean kvps;
    private final Collection<CompanyContractStateDataDTO> states;

    public CompanyContractTypeDataDTO(@JsonProperty("matchcode") ContractTypeMatchcode matchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("kvps") boolean kvps, @JsonProperty("states") Collection<CompanyContractStateDataDTO> states)
    {
        super();

        this.matchcode = matchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.kvps = kvps;
        this.states = states;
    }

    @Override
    public ContractTypeMatchcode getMatchcode()
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

    public boolean isKvps()
    {
        return kvps;
    }

    public Collection<CompanyContractStateDataDTO> getStates()
    {
        return states;
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

        CompanyContractTypeDataDTO other = (CompanyContractTypeDataDTO) obj;

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
        return String.format("%s [validFrom=%s, validTo=%s, kvps=%s, states=%s]", matchcode, validFrom, validTo, kvps,
            states);
    }

}
