package pnet.data.api.companygroup;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithCompanyId;

/**
 * Holds minimal information about a company group member.
 *
 * @author cet
 *
 */
@ApiModel(description = "Holds minimal information about a company group member")
public class CompanyGroupMemberLinkDTO implements WithCompanyId
{
    @ApiModelProperty(notes = "The unique id of the company that is in the company group.")
    private final Integer companyId;

    @ApiModelProperty(notes = "The (almost-unique) matchcode of the company that is in the company group.")
    private final String companyMatchcode;

    @ApiModelProperty(notes = "The (non-unique) number of the company that is in the company group.")
    private final String companyNumber;

    @ApiModelProperty(notes = "The unique matchcode of the company group type of the company group")
    private final String groupType;

    public CompanyGroupMemberLinkDTO(@JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode, @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("groupType") String groupType)
    {
        super();
        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.groupType = groupType;
    }

    @Override
    public Integer getCompanyId()
    {
        return companyId;
    }

    @Override
    public String getCompanyMatchcode()
    {
        return companyMatchcode;
    }

    @Override
    public String getCompanyNumber()
    {
        return companyNumber;
    }

    public String getGroupType()
    {
        return groupType;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + ((groupType == null) ? 0 : groupType.hashCode());
        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        if (!(obj instanceof CompanyGroupMemberLinkDTO))
        {
            return false;
        }

        CompanyGroupMemberLinkDTO other = (CompanyGroupMemberLinkDTO) obj;

        if (groupType == null)
        {
            if (other.groupType != null)
            {
                return false;
            }
        }
        else if (!groupType.equals(other.groupType))
        {
            return false;
        }
        if (companyId == null)
        {
            if (other.companyId != null)
            {
                return false;
            }
        }
        else if (!companyId.equals(other.companyId))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s:%s", groupType, companyId);
    }

}
