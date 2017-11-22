package pnet.data.api.company;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.contractstate.ContractStateMatchcode;

/**
 * A link to a contract state
 *
 * @author ham
 */
public class CompanyContractStateLinkDTO
{

    private final ContractStateMatchcode contractStateMatchcode;
    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;

    public CompanyContractStateLinkDTO(@JsonProperty("matchcode") ContractStateMatchcode contractStateMatchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo)
    {
        super();

        this.contractStateMatchcode = contractStateMatchcode;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public ContractStateMatchcode getMatchcode()
    {
        return contractStateMatchcode;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((contractStateMatchcode == null) ? 0 : contractStateMatchcode.hashCode());
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

        CompanyContractStateLinkDTO other = (CompanyContractStateLinkDTO) obj;

        if (contractStateMatchcode == null)
        {
            if (other.contractStateMatchcode != null)
            {
                return false;
            }
        }
        else if (!contractStateMatchcode.equals(other.contractStateMatchcode))
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
        return String.format("%s [validFrom=%s, validTo=%s]", contractStateMatchcode, validFrom, validTo);
    }

}
