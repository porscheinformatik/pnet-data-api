package pnet.data.api.companygroup;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.company.CompanyContractTypeLinkDTO;

/**
 * Holds minimal information about a company group member.
 *
 * @author cet
 *
 */
@ApiModel(description = "Holds minimal information about a company group member")
public class CompanyGroupMemberLinkDTO
{
    @ApiModelProperty(notes = "The unique matchcode of the company group type of the company group")
    private String groupType;
    @ApiModelProperty(notes = "The unique id of the company that is in the company group")
    private Integer companyId;

    public CompanyGroupMemberLinkDTO(@JsonProperty("companyId") Integer companyId,
        @JsonProperty("groupType") String groupType)
    {
        super();
        this.groupType = groupType;
        this.companyId = companyId;
    }

    public String getGroupType()
    {
        return groupType;
    }

    public void setGroupType(String groupType)
    {
        this.groupType = groupType;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
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
        if (!(obj instanceof CompanyContractTypeLinkDTO))
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

}
