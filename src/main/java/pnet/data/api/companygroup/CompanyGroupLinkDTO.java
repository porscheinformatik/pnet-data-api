package pnet.data.api.companygroup;

import java.util.Collection;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.companygrouptype.CompanyGroupTypeMatchcode;

/**
 * Holds a group of companies.
 *
 * @author ham
 */
public class CompanyGroupLinkDTO
{

    private final Integer leadingCompanyId;
    private final CompanyGroupTypeMatchcode type;
    private final Collection<Integer> companyIds;

    public CompanyGroupLinkDTO(@JsonProperty("leadingCompanyId") Integer leadingCompanyId,
        @JsonProperty("type") CompanyGroupTypeMatchcode type,
        @JsonProperty("companyIds") Collection<Integer> companyIds)
    {
        super();

        this.leadingCompanyId = leadingCompanyId;
        this.type = type;
        this.companyIds = Collections.unmodifiableCollection(companyIds);
    }

    public Integer getLeadingCompanyId()
    {
        return leadingCompanyId;
    }

    public CompanyGroupTypeMatchcode getType()
    {
        return type;
    }

    public Collection<Integer> getCompanyIds()
    {
        return companyIds;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((leadingCompanyId == null) ? 0 : leadingCompanyId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());

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

        CompanyGroupLinkDTO other = (CompanyGroupLinkDTO) obj;

        if (leadingCompanyId == null)
        {
            if (other.leadingCompanyId != null)
            {
                return false;
            }
        }
        else if (!leadingCompanyId.equals(other.leadingCompanyId))
        {
            return false;
        }

        if (type == null)
        {
            if (other.type != null)
            {
                return false;
            }
        }
        else if (!type.equals(other.type))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("CompanyGroupDataDTO [leadingCompanyId=%s, type=%s, companyIds=%s]", leadingCompanyId,
            type, companyIds);
    }

}
