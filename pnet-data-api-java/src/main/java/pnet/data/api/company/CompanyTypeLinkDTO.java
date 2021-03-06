package pnet.data.api.company;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithMatchcode;

/**
 * A link to a company type
 *
 * @author HAM
 */
@ApiModel(description = "Holds minimal information about a type of the company. The matchcode fits the matchcodes of "
    + "the company types interface.")
public class CompanyTypeLinkDTO implements WithMatchcode, Serializable
{

    private static final long serialVersionUID = -6736388715804866171L;

    @ApiModelProperty(notes = "The matchcode of the type (fits the matchcodes of the company types interface).")
    private final String matchcode;

    public CompanyTypeLinkDTO(@JsonProperty("matchcode") String matchcode)
    {
        super();
        this.matchcode = matchcode;
    }

    @Override
    public String getMatchcode()
    {
        return matchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());
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
        if (!(obj instanceof CompanyTypeLinkDTO))
        {
            return false;
        }
        CompanyTypeLinkDTO other = (CompanyTypeLinkDTO) obj;
        if (matchcode == null)
        {
            if (other.matchcode != null)
            {
                return false;
            }
        }
        else if (!matchcode.equals(other.matchcode))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s", matchcode);
    }

}
