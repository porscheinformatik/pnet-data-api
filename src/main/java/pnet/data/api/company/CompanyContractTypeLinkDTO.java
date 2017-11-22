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

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.contracttype.ContractTypeMatchcode;

/**
 * Holds the contracts of a company.
 *
 * @author ham
 */
public class CompanyContractTypeLinkDTO
{

    private final ContractTypeMatchcode contractTypeMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final boolean kvps;
    private final Collection<CompanyContractStateLinkDTO> states;

    public CompanyContractTypeLinkDTO(
        @JsonProperty("contractTypeMatchcode") ContractTypeMatchcode contractTypeMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("kvps") boolean kvps, @JsonProperty("states") Collection<CompanyContractStateLinkDTO> states)
    {
        super();

        this.contractTypeMatchcode = contractTypeMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.kvps = kvps;
        this.states = states;
    }

    public ContractTypeMatchcode getContractTypeMatchcode()
    {
        return contractTypeMatchcode;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    public boolean isKvps()
    {
        return kvps;
    }

    public Collection<CompanyContractStateLinkDTO> getStates()
    {
        return states;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
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
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        CompanyContractTypeLinkDTO other = (CompanyContractTypeLinkDTO) obj;
        if (contractTypeMatchcode == null)
        {
            if (other.contractTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!contractTypeMatchcode.equals(other.contractTypeMatchcode))
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
        return String.format("%s [validFrom=%s, validTo=%s, kvps=%s, states=%s]", contractTypeMatchcode, validFrom,
            validTo, kvps, states);
    }

}
