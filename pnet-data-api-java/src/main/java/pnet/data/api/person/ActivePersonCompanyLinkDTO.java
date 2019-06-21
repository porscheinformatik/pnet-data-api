package pnet.data.api.person;

import java.io.Serializable;
import java.util.Objects;

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

    @ApiModelProperty(notes = "The label of the company the person has an employment at.")
    protected final String companyLabel;

    public ActivePersonCompanyLinkDTO(@JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode, @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("companyLabel") String companyLabel)
    {
        super();

        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.companyLabel = companyLabel;
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

    public String getCompanyLabel()
    {
        return companyLabel;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(companyId);
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
        return Objects.equals(companyId, other.companyId);
    }

    @Override
    public String toString()
    {
        return String
            .format("ActivePersonCompanyLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s]", companyId,
                companyMatchcode, companyNumber);
    }

}