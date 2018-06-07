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
package pnet.data.api.person;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenant;
import pnet.data.api.util.WithValidPeriod;

/**
 * Holds the brand of a company with all contracts for the brand.
 *
 * @author ham
 */
public class PersonBrandDataDTO implements WithTenant, WithMatchcode, WithValidPeriod, Serializable
{

    private static final long serialVersionUID = 4304701417184336190L;

    private final String tenant;
    private final String matchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final Collection<PersonFunctionDataDTO> functions;
    private final Collection<PersonActivityDataDTO> activities;

    public PersonBrandDataDTO(@JsonProperty("tenant") String tenant, @JsonProperty("matchcode") String matchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("functions") Collection<PersonFunctionDataDTO> functions,
        @JsonProperty("activities") Collection<PersonActivityDataDTO> activities)
    {
        this.tenant = tenant;
        this.matchcode = matchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.functions = Collections.unmodifiableCollection(Objects.requireNonNull(functions, "Functions are null"));
        this.activities = Collections.unmodifiableCollection(Objects.requireNonNull(activities, "Activities are null"));
    }

    @Override
    public String getTenant()
    {
        return tenant;
    }

    @Override
    public String getMatchcode()
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

    public Collection<PersonFunctionDataDTO> getFunctions()
    {
        return functions;
    }

    public Collection<PersonActivityDataDTO> getActivities()
    {
        return activities;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());
        result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());
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

        PersonBrandDataDTO other = (PersonBrandDataDTO) obj;

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
        return String.format("%s(%s) [validFrom=%s, validTo=%s, functions=%s, activities=%s]", matchcode, tenant,
            validFrom, validTo, functions, activities);
    }

}
