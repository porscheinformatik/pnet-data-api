package pnet.data.api.function;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.brand.BrandLink;
import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds a link to a brand.
 *
 * @author ham
 */
public class FunctionBrandLinkDTO implements BrandLink
{

    private final Tenant tenant;
    private final BrandMatchcode brandMatchcode;

    public FunctionBrandLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("brandMatchcode") BrandMatchcode brandMatchcode)
    {
        this.tenant = tenant;
        this.brandMatchcode = brandMatchcode;
    }

    @Override
    public Tenant getTenant()
    {
        return tenant;
    }

    @Override
    public BrandMatchcode getBrandMatchcode()
    {
        return brandMatchcode;
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
        FunctionBrandLinkDTO other = (FunctionBrandLinkDTO) obj;
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
        return String.format("%s(%s)", brandMatchcode, tenant);
    }

}
