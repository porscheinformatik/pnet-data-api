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
package pnet.data.api.function;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.Tenant;
import pnet.data.api.contracttype.ContractTypeMatchcode;

/**
 * Holds a link to a contract type.
 *
 * @author ham
 */
public class FunctionContractTypeDataDTO
{

    private final Tenant tenant;
    private final ContractTypeMatchcode contractTypeMatchcode;

    public FunctionContractTypeDataDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("contractTypeMatchcode") ContractTypeMatchcode contractTypeMatchcode)
    {
        super();

        this.tenant = tenant;
        this.contractTypeMatchcode = contractTypeMatchcode;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public ContractTypeMatchcode getContractTypeMatchcode()
    {
        return contractTypeMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((contractTypeMatchcode == null) ? 0 : contractTypeMatchcode.hashCode());
        result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());

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

        FunctionContractTypeDataDTO other = (FunctionContractTypeDataDTO) obj;

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

        if (tenant == null)
        {
            if (other.tenant != null)
            {
                return false;
            }
        }
        else if (!tenant.equals(other.tenant))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s(%s)", contractTypeMatchcode, tenant);
    }

}
