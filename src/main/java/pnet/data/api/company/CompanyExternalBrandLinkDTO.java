package pnet.data.api.company;

import java.time.LocalDateTime;

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

    public CompanyExternalBrandLinkDTO(ExternalBrandMatchcode externalBrandMatchcode, LocalDateTime validFrom,
        LocalDateTime validTo, boolean sales, boolean service, boolean local)
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
