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

import pnet.data.api.contractstate.ContractStateMatchcode;

/**
 * A link to a contract state
 *
 * @author ham
 */
public class CompanyContractStateDataDTO implements Serializable
{

    private static final long serialVersionUID = 8013176883992921779L;
    
    private final ContractStateMatchcode contractStateMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;

    public CompanyContractStateDataDTO(@JsonProperty("matchcode") ContractStateMatchcode contractStateMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo)
    {
        super();

        this.contractStateMatchcode = contractStateMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public ContractStateMatchcode getMatchcode()
    {
        return contractStateMatchcode;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((contractStateMatchcode == null) ? 0 : contractStateMatchcode.hashCode());
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

        CompanyContractStateDataDTO other = (CompanyContractStateDataDTO) obj;

        if (contractStateMatchcode == null)
        {
            if (other.contractStateMatchcode != null)
            {
                return false;
            }
        }
        else if (!contractStateMatchcode.equals(other.contractStateMatchcode))
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
        return String.format("%s [validFrom=%s, validTo=%s]", contractStateMatchcode, validFrom, validTo);
    }

}
