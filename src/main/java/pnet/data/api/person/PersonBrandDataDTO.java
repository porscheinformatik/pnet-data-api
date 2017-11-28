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

import pnet.data.api.Tenant;
import pnet.data.api.brand.BrandMatchcode;

/**
 * Holds the brand of a company with all contracts for the brand.
 *
 * @author ham
 */
public class PersonBrandDataDTO implements Serializable
{

    private static final long serialVersionUID = 4304701417184336190L;
    
    private final Tenant tenant;
    private final BrandMatchcode brandMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final Collection<PersonFunctionDataDTO> functions;
    private final Collection<PersonActivityDataDTO> activities;
    private final Collection<PersonInfoareaDataDTO> infoareas;

    public PersonBrandDataDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("brandMatchcode") BrandMatchcode brandMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("functions") Collection<PersonFunctionDataDTO> functions,
        @JsonProperty("activities") Collection<PersonActivityDataDTO> activities,
        @JsonProperty("infoareas") Collection<PersonInfoareaDataDTO> infoareas)
    {
        this.tenant = tenant;
        this.brandMatchcode = brandMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.functions = Collections.unmodifiableCollection(Objects.requireNonNull(functions, "Functions are null"));
        this.activities = Collections.unmodifiableCollection(Objects.requireNonNull(activities, "Activities are null"));
        this.infoareas = Collections.unmodifiableCollection(Objects.requireNonNull(infoareas, "Infoareas are null"));
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public BrandMatchcode getBrandMatchcode()
    {
        return brandMatchcode;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

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

    public Collection<PersonInfoareaDataDTO> getInfoareas()
    {
        return infoareas;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((brandMatchcode == null) ? 0 : brandMatchcode.hashCode());
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
        return String.format("%s(%s) [validFrom=%s, validTo=%s, functions=%s, activities=%s]", brandMatchcode, tenant,
            validFrom, validTo, functions, activities);
    }

}
