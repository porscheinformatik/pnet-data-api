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
package pnet.data.api.contracttype;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.Tenant;
import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.contractstate.ContractStateMatchcode;

/**
 * A link to a brand for a specified tenant.
 *
 * @author ham
 */
public class ContractTypeBrandDataDTO
{

    private final Tenant tenant;
    private final BrandMatchcode brandMatchcode;
    private final Collection<ContractStateMatchcode> states;

    public ContractTypeBrandDataDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("brandMatchcode") BrandMatchcode brandMatchcode,
        @JsonProperty("states") Collection<ContractStateMatchcode> states)
    {
        super();
        this.tenant = tenant;
        this.brandMatchcode = brandMatchcode;
        this.states = states;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public BrandMatchcode getBrandMatchcode()
    {
        return brandMatchcode;
    }

    public Collection<ContractStateMatchcode> getStates()
    {
        return states;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((brandMatchcode == null) ? 0 : brandMatchcode.hashCode());
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

        ContractTypeBrandDataDTO other = (ContractTypeBrandDataDTO) obj;

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
        return String.format("%s(%s) [states=%s]", brandMatchcode, tenant, states);
    }

}
