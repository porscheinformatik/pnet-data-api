package pnet.data.api.company;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds the brand of a company with all contracts for the brand.
 *
 * @author ham
 */
public class CompanyBrandLinkDTO
{

    private final Tenant tenant;
    private final BrandMatchcode brandMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;
    private final Collection<CompanyContractTypeLinkDTO> contracts;

    public CompanyBrandLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("brandMatchcode") BrandMatchcode brandMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo,
        @JsonProperty("contracts") Collection<CompanyContractTypeLinkDTO> contracts)
    {
        this.tenant = tenant;
        this.brandMatchcode = brandMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.contracts = Collections.unmodifiableCollection(Objects.requireNonNull(contracts, "Contrats are null"));
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

    public Collection<CompanyContractTypeLinkDTO> getContracts()
    {
        return contracts;
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
        CompanyBrandLinkDTO other = (CompanyBrandLinkDTO) obj;
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
        return String.format("%s(%s) [validFrom=%s, validTo=%s, contracts=%s]", brandMatchcode, tenant, validFrom,
            validTo, contracts);
    }

}
