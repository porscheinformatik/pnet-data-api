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
package pnet.data.api.activity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.Tenant;
import pnet.data.api.companytype.CompanyTypeMatchcode;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenant;

/**
 * Holds a company type.
 *
 * @author ham
 */
public class ActivityCompanyTypeDataDTO implements WithTenant, WithMatchcode<CompanyTypeMatchcode>, Serializable
{

    private static final long serialVersionUID = -2028835160784471478L;

    private final Tenant tenant;
    private final CompanyTypeMatchcode matchcode;

    public ActivityCompanyTypeDataDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("matchcode") CompanyTypeMatchcode matchcode)
    {
        super();

        this.tenant = tenant;
        this.matchcode = matchcode;
    }

    @Override
    public Tenant getTenant()
    {
        return tenant;
    }

    @Override
    public CompanyTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());
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

        ActivityCompanyTypeDataDTO other = (ActivityCompanyTypeDataDTO) obj;

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
        return String.format("%s(%s)", matchcode, tenant);
    }

}
