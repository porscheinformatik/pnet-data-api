package pnet.data.api.person;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithCompanyId;

/**
 * Holds one employment of a person
 *
 * @author HAM
 *
 */
@ApiModel(description = "Holds minimal information about a employment the person has.")
public class ActivePersonCompanyLinkDTO implements WithCompanyId, Serializable
{

    private static final long serialVersionUID = -847403488657179223L;

    @ApiModelProperty(notes = "The unique id of the company the person has an employment at.")
    protected final Integer companyId;

    @ApiModelProperty(notes = "The matchcode of the company the person has an employment at.")
    protected final String companyMatchcode;

    @ApiModelProperty(notes = "The number of the company the person has an employment at.")
    protected final String companyNumber;

    @ApiModelProperty(notes = "The matchcodes of the main functions the person has at the employment")
    protected final List<String> mainFunctionMatchcodes;

    public ActivePersonCompanyLinkDTO(@JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode, @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("mainFunctionMatchcodes") List<String> mainFunctionMatchcodes)
    {
        super();

        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.mainFunctionMatchcodes = mainFunctionMatchcodes;
    }

    @Override
    public Integer getCompanyId()
    {
        return companyId;
    }

    public String getCompanyMatchcode()
    {
        return companyMatchcode;
    }

    public String getCompanyNumber()
    {
        return companyNumber;
    }

    public List<String> getMainFunctionMatchcodes()
    {
        return mainFunctionMatchcodes;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
        result = prime * result + ((mainFunctionMatchcodes == null) ? 0 : mainFunctionMatchcodes.hashCode());
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
        if (!(obj instanceof ActivePersonCompanyLinkDTO))
        {
            return false;
        }
        ActivePersonCompanyLinkDTO other = (ActivePersonCompanyLinkDTO) obj;
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
        if (mainFunctionMatchcodes == null)
        {
            if (other.mainFunctionMatchcodes != null)
            {
                return false;
            }
        }
        else if (!mainFunctionMatchcodes.equals(other.mainFunctionMatchcodes))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "ActivePersonCompanyLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, mainFunctionMatchcodes=%s]",
                companyId, companyMatchcode, companyNumber, mainFunctionMatchcodes);
    }

}