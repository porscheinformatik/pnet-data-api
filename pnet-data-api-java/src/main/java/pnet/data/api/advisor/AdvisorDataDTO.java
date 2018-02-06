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
package pnet.data.api.advisor;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithTenant;

/**
 * Holds all advisors
 *
 * @author ham
 */
public class AdvisorDataDTO implements WithTenant, Serializable
{

    private static final long serialVersionUID = -5535179500060765387L;

    private final String tenant;
    private final String brandMatchcode;
    private final String advisorTypeMatchcode;
    private final String advisorDivisionMatchcode;
    private final Integer companyId;
    private final Integer personId;
    private final LocalDateTime lastUpdate;

    public AdvisorDataDTO(@JsonProperty("tenant") String tenant, @JsonProperty("brandMatchcode") String brandMatchcode,
        @JsonProperty("advisorTypeMatchcode") String advisorTypeMatchcode,
        @JsonProperty("advisorDivisionMatchcode") String advisorDivisionMatchcode,
        @JsonProperty("companyId") Integer companyId, @JsonProperty("personId") Integer personId,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();
        this.tenant = tenant;
        this.brandMatchcode = brandMatchcode;
        this.advisorTypeMatchcode = advisorTypeMatchcode;
        this.advisorDivisionMatchcode = advisorDivisionMatchcode;
        this.companyId = companyId;
        this.personId = personId;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String getTenant()
    {
        return tenant;
    }

    public String getBrandMatchcode()
    {
        return brandMatchcode;
    }

    public String getAdvisorTypeMatchcode()
    {
        return advisorTypeMatchcode;
    }

    public String getAdvisorDivisionMatchcode()
    {
        return advisorDivisionMatchcode;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public Integer getPersonId()
    {
        return personId;
    }

    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((advisorDivisionMatchcode == null) ? 0 : advisorDivisionMatchcode.hashCode());
        result = prime * result + ((advisorTypeMatchcode == null) ? 0 : advisorTypeMatchcode.hashCode());
        result = prime * result + ((brandMatchcode == null) ? 0 : brandMatchcode.hashCode());
        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());

        return result;
    }

    // CHECKSTYLE:OFF
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

        AdvisorDataDTO other = (AdvisorDataDTO) obj;

        if (advisorDivisionMatchcode == null)
        {
            if (other.advisorDivisionMatchcode != null)
            {
                return false;
            }
        }
        else if (!advisorDivisionMatchcode.equals(other.advisorDivisionMatchcode))
        {
            return false;
        }

        if (advisorTypeMatchcode == null)
        {
            if (other.advisorTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!advisorTypeMatchcode.equals(other.advisorTypeMatchcode))
        {
            return false;
        }

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

        if (companyId == null)
        {
            if (other.companyId != null)
            {
                return false;
            }
        }
        else if (!companyId.equals(other.companyId))
        {
            return false;
        }

        if (personId == null)
        {
            if (other.personId != null)
            {
                return false;
            }
        }
        else if (!personId.equals(other.personId))
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
    // CHECKSTYLE:ON

    @Override
    public String toString()
    {
        return String.format(
            "AdvisorDataDTO [tenant=%s, brandMatchcode=%s, advisorTypeMatchcode=%s, advisorDivisionMatchcode=%s, companyId=%s, personId=%s, lastUpdate=%s]",
            tenant, brandMatchcode, advisorTypeMatchcode, advisorDivisionMatchcode, companyId, personId, lastUpdate);
    }

}
