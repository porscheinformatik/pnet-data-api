package pnet.data.api.contracttype;

import java.util.Collection;

import pnet.data.api.brand.BrandLink;
import pnet.data.api.brand.BrandMatchcode;
import pnet.data.api.contractstate.ContractStateMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * A link to a brand for a specified tenant.
 *
 * @author ham
 */
public class ContractTypeBrandLinkDTO implements BrandLink
{

    private final Tenant tenant;
    private final BrandMatchcode brandMatchcode;
    private final Collection<ContractStateMatchcode> states;

    public ContractTypeBrandLinkDTO(Tenant tenant, BrandMatchcode brandMatchcode,
        Collection<ContractStateMatchcode> states)
    {
        super();
        this.tenant = tenant;
        this.brandMatchcode = brandMatchcode;
        this.states = states;
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

    public Collection<ContractStateMatchcode> getStates()
    {
        return states;
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
        ContractTypeBrandLinkDTO other = (ContractTypeBrandLinkDTO) obj;
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
        return String.format("%s(%s) [states=%s]", brandMatchcode, tenant, states);
    }

}
