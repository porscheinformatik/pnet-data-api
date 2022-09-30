package pnet.data.api.person;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.AbstractLinkDTO;
import pnet.data.api.util.WithBrandMatchcode;
import pnet.data.api.util.WithCompanyId;

/**
 * Abstract base class for function references.
 *
 * @author HAM
 */
public abstract class AbstractPersonFunctionLinkDTO extends AbstractLinkDTO implements WithCompanyId, WithBrandMatchcode
{

    private static final long serialVersionUID = -7454296829315458948L;

    @ApiModelProperty(notes = "The id of the company at which the function is assigned to the person.")
    protected final Integer companyId;

    @ApiModelProperty(notes = "The matchcode of the company at which the function is assigned to the person.")
    protected final String companyMatchcode;

    @ApiModelProperty(notes = "The number of the company at which the function is assigned to the person.")
    protected final String companyNumber;

    @ApiModelProperty(notes = "The brand to which the function is assigned to the person.")
    protected final String brandMatchcode;

    @ApiModelProperty(notes = "The flag that declares, whether this function is the "
        + "main function of the person at the specific company or not.")
    protected final boolean mainFunction;

    public AbstractPersonFunctionLinkDTO(@JsonProperty("tenant") String tenant,
        @JsonProperty("matchcode") String matchcode, @JsonProperty("companyId") Integer companyId,
        @JsonProperty("companyMatchcode") String companyMatchcode, @JsonProperty("companyNumber") String companyNumber,
        @JsonProperty("brandMatchcode") String brandMatchcode, @JsonProperty("mainFunction") boolean mainFunction)
    {
        super(tenant, matchcode);
        this.companyId = companyId;
        this.companyMatchcode = companyMatchcode;
        this.companyNumber = companyNumber;
        this.brandMatchcode = brandMatchcode;
        this.mainFunction = mainFunction;
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

    @Override
    public String getBrandMatchcode()
    {
        return brandMatchcode;
    }

    public boolean isMainFunction()
    {
        return mainFunction;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(brandMatchcode, companyId);
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
        if (!(obj instanceof AbstractPersonFunctionLinkDTO))
        {
            return false;
        }
        AbstractPersonFunctionLinkDTO other = (AbstractPersonFunctionLinkDTO) obj;
        return Objects.equals(brandMatchcode, other.brandMatchcode) && Objects.equals(companyId, other.companyId);
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "AbstractPersonFunctionLinkDTO [companyId=%s, companyMatchcode=%s, companyNumber=%s, "
                    + "brandMatchcode=%s, mainFunction=%s, getTenant()=%s, getMatchcode()=%s]",
                companyId, companyMatchcode, companyNumber, brandMatchcode, mainFunction, getTenant(), getMatchcode());
    }

}