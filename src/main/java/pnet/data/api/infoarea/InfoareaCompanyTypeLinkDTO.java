package pnet.data.api.infoarea;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.companytype.CompanyTypeLink;
import pnet.data.api.companytype.CompanyTypeMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds a link to a company type.
 *
 * @author ham
 */
public class InfoareaCompanyTypeLinkDTO implements CompanyTypeLink
{

    private final Tenant tenant;
    private final CompanyTypeMatchcode companyTypeMatchcode;

    public InfoareaCompanyTypeLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("companyTypeMatchcode") CompanyTypeMatchcode companyTypeMatchcode)
    {
        super();

        this.tenant = tenant;
        this.companyTypeMatchcode = companyTypeMatchcode;
    }

    @Override
    public Tenant getTenant()
    {
        return tenant;
    }

    @Override
    public CompanyTypeMatchcode getCompanyTypeMatchcode()
    {
        return companyTypeMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((companyTypeMatchcode == null) ? 0 : companyTypeMatchcode.hashCode());
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

        InfoareaCompanyTypeLinkDTO other = (InfoareaCompanyTypeLinkDTO) obj;

        if (companyTypeMatchcode == null)
        {
            if (other.companyTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!companyTypeMatchcode.equals(other.companyTypeMatchcode))
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
        return String.format("%s(%s)", companyTypeMatchcode, tenant);
    }

}
