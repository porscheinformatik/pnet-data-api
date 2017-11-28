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
package pnet.data.api.infoarea;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.Tenant;
import pnet.data.api.companytype.CompanyTypeMatchcode;

/**
 * Holds a link to a company type.
 *
 * @author ham
 */
public class InfoareaCompanyTypeDataDTO
{

    private final Tenant tenant;
    private final CompanyTypeMatchcode companyTypeMatchcode;

    public InfoareaCompanyTypeDataDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("companyTypeMatchcode") CompanyTypeMatchcode companyTypeMatchcode)
    {
        super();

        this.tenant = tenant;
        this.companyTypeMatchcode = companyTypeMatchcode;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public CompanyTypeMatchcode getCompanyTypeMatchcode()
    {
        return companyTypeMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((companyTypeMatchcode == null) ? 0 : companyTypeMatchcode.hashCode());
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

        InfoareaCompanyTypeDataDTO other = (InfoareaCompanyTypeDataDTO) obj;

        if (companyTypeMatchcode == null)
        {
            if (other.companyTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!companyTypeMatchcode.equals(other.companyTypeMatchcode))
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
        return String.format("%s(%s)", companyTypeMatchcode, tenant);
    }

}
