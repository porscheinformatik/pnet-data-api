package pnet.data.api.function;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.contracttype.ContractTypeMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds a link to a contract type.
 *
 * @author ham
 */
public class FunctionContractTypeLinkDTO
{

    private final Tenant tenant;
    private final ContractTypeMatchcode contractTypeMatchcode;

    public FunctionContractTypeLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("contractTypeMatchcode") ContractTypeMatchcode contractTypeMatchcode)
    {
        super();

        this.tenant = tenant;
        this.contractTypeMatchcode = contractTypeMatchcode;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public ContractTypeMatchcode getContractTypeMatchcode()
    {
        return contractTypeMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((contractTypeMatchcode == null) ? 0 : contractTypeMatchcode.hashCode());
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

        FunctionContractTypeLinkDTO other = (FunctionContractTypeLinkDTO) obj;

        if (contractTypeMatchcode == null)
        {
            if (other.contractTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!contractTypeMatchcode.equals(other.contractTypeMatchcode))
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
        return String.format("%s(%s)", contractTypeMatchcode, tenant);
    }

}
