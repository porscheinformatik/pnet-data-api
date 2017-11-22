package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.companynumbertype.CompanyNumberTypeMatchcode;

/**
 * Holds an additional company number.
 *
 * @author ham
 */
public class CompanyNumberLinkDTO
{

    private final CompanyNumberTypeMatchcode companyNumberTypeMatchcode;
    private final String number;

    public CompanyNumberLinkDTO(
        @JsonProperty("companyNumberTypeMatchcode") CompanyNumberTypeMatchcode companyNumberTypeMatchcode,
        @JsonProperty("number") String number)
    {
        super();

        this.companyNumberTypeMatchcode = companyNumberTypeMatchcode;
        this.number = number;
    }

    public CompanyNumberTypeMatchcode getCompanyNumberTypeMatchcode()
    {
        return companyNumberTypeMatchcode;
    }

    public String getNumber()
    {
        return number;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((companyNumberTypeMatchcode == null) ? 0 : companyNumberTypeMatchcode.hashCode());

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

        CompanyNumberLinkDTO other = (CompanyNumberLinkDTO) obj;

        if (companyNumberTypeMatchcode == null)
        {
            if (other.companyNumberTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!companyNumberTypeMatchcode.equals(other.companyNumberTypeMatchcode))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s [number=%s]", super.toString(), number);
    }

}
