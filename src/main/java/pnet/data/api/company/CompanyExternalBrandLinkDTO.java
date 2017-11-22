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

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.externalbrand.ExternalBrandMatchcode;

/**
 * An external brand of a company.
 *
 * @author ham
 */
public class CompanyExternalBrandLinkDTO
{

    private final ExternalBrandMatchcode externalBrandMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final boolean sales;
    private final boolean service;
    private final boolean local;

    public CompanyExternalBrandLinkDTO(
        @JsonProperty("externalBrandMatchcode") ExternalBrandMatchcode externalBrandMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("sales") boolean sales, @JsonProperty("service") boolean service,
        @JsonProperty("local") boolean local)
    {
        super();

        this.externalBrandMatchcode = externalBrandMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.sales = sales;
        this.service = service;
        this.local = local;
    }

    public ExternalBrandMatchcode getExternalBrandMatchcode()
    {
        return externalBrandMatchcode;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    public boolean isSales()
    {
        return sales;
    }

    public boolean isService()
    {
        return service;
    }

    public boolean isLocal()
    {
        return local;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((externalBrandMatchcode == null) ? 0 : externalBrandMatchcode.hashCode());
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

        CompanyExternalBrandLinkDTO other = (CompanyExternalBrandLinkDTO) obj;

        if (externalBrandMatchcode == null)
        {
            if (other.externalBrandMatchcode != null)
            {
                return false;
            }
        }
        else if (!externalBrandMatchcode.equals(other.externalBrandMatchcode))
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
        return String.format("%s [validFrom=%s, validTo=%s, sales=%s, service=%s, local=%s]", externalBrandMatchcode,
            validFrom, validTo, sales, service, local);
    }

}
