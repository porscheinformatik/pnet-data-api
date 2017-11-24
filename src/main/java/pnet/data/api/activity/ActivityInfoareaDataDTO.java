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

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.infoarea.InfoareaMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds a link to an infoarea
 *
 * @author ham
 */
public class ActivityInfoareaDataDTO
{

    private final Tenant tenant;
    private final InfoareaMatchcode infoareaMatchcode;

    public ActivityInfoareaDataDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("infoareaMatchcode") InfoareaMatchcode infoareaMatchcode)
    {
        super();

        this.tenant = tenant;
        this.infoareaMatchcode = infoareaMatchcode;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public InfoareaMatchcode getInfoareaMatchcode()
    {
        return infoareaMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((infoareaMatchcode == null) ? 0 : infoareaMatchcode.hashCode());
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

        ActivityInfoareaDataDTO other = (ActivityInfoareaDataDTO) obj;

        if (infoareaMatchcode == null)
        {
            if (other.infoareaMatchcode != null)
            {
                return false;
            }
        }
        else if (!infoareaMatchcode.equals(other.infoareaMatchcode))
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
        return String.format("%s(%s)", infoareaMatchcode, tenant);
    }

}
