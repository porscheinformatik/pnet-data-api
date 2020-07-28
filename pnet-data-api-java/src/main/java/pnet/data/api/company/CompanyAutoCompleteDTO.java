package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.GeoPoint;
import pnet.data.api.util.AbstractAutoCompleteDTO;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * Holds the result of an auto complete query.
 *
 * @author HAM
 */
public class CompanyAutoCompleteDTO extends AbstractAutoCompleteDTO
{

    private static final long serialVersionUID = -6275336068143194073L;

    @ApiModelProperty(notes = "The unique id of the company.")
    private final Integer companyId;

    @ApiModelProperty(
        notes = "The matchcode of the company (a combination of administrative tenant and company number).")
    private final String matchcode;

    @ApiModelProperty(notes = "The tenant (Portal-ID), in which this company gets administrated.")
    private final String administrativeTenant;

    @ApiModelProperty(notes = "The company number. In most cases, this is the same as the SAP number.")
    private final String companyNumber;

    @ApiModelProperty(notes = "The logitude and latitude of the companies location.")
    private final GeoPoint location;

    public CompanyAutoCompleteDTO(@JsonProperty("companyId") Integer companyId,
        @JsonProperty("matchcode") String matchcode, @JsonProperty("administrativeTenant") String administrativeTenant,
        @JsonProperty("label") String label, @JsonProperty("description") String description,
        @JsonProperty("companyNumber") String companyNumber, @JsonProperty("location") GeoPoint location,
        @JsonProperty("score") double score)
    {
        super(label, description, score);
        this.companyId = companyId;
        this.matchcode = matchcode;
        this.administrativeTenant = administrativeTenant;
        this.companyNumber = companyNumber;
        this.location = location;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public String getMatchcode()
    {
        return matchcode;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public String getLabelWithNumber()
    {
        return PnetDataApiUtils.toCompanyLabelWithNumber(companyNumber, getLabel());
    }

    public String getCompanyNumber()
    {
        return companyNumber;
    }

    public GeoPoint getLocation()
    {
        return location;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "CompanyAutoCompleteDTO [companyId=%s, matchcode=%s, administrativeTenant=%s, companyNumber=%s, "
                    + "location=%s, getLabel()=%s, getDescription()=%s, getScore()=%s]",
                companyId, matchcode, administrativeTenant, companyNumber, location, getLabel(), getDescription(),
                getScore());
    }

}