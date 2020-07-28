package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.AbstractAutoCompleteDTO;

/**
 * Holds the result of an auto complete query.
 *
 * @author HAM
 */
public class PersonAutoCompleteDTO extends AbstractAutoCompleteDTO
{

    private static final long serialVersionUID = -6275336068143194073L;

    @ApiModelProperty(notes = "The unique id of the person.")
    private final Integer personId;

    @ApiModelProperty(notes = "The tenant, in which this person is administrated.")
    private final String administrativeTenant;

    @ApiModelProperty(notes = "The external id of the person.")
    private final String externalId;

    @ApiModelProperty(notes = "The global user id of the person.")
    private final String guid;

    @ApiModelProperty(notes = "The preferred user id of the person.")
    private final String preferredUserId;

    @ApiModelProperty(notes = "The personnel number of the person.")
    private final String personnelNumber;

    @ApiModelProperty(notes = "The email of the person.")
    private final String email;

    public PersonAutoCompleteDTO(@JsonProperty("personId") Integer personId,
        @JsonProperty("administrativeTenant") String administrativeTenant, @JsonProperty("label") String label,
        @JsonProperty("description") String description, @JsonProperty("externalId") String externalId,
        @JsonProperty("guid") String guid, @JsonProperty("preferredUserId") String preferredUserId,
        @JsonProperty("personnelNumber") String personnelNumber, @JsonProperty("email") String email,
        @JsonProperty("score") double score)
    {
        super(label, description, score);

        this.personId = personId;
        this.administrativeTenant = administrativeTenant;
        this.externalId = externalId;
        this.guid = guid;
        this.preferredUserId = preferredUserId;
        this.personnelNumber = personnelNumber;
        this.email = email;
    }

    public Integer getPersonId()
    {
        return personId;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public String getExternalId()
    {
        return externalId;
    }

    public String getGuid()
    {
        return guid;
    }

    public String getPreferredUserId()
    {
        return preferredUserId;
    }

    public String getPersonnelNumber()
    {
        return personnelNumber;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return String
            .format("PersonAutoCompleteDTO [personId=%s, administrativeTenant=%s, externalId=%s, guid=%s, "
                + "preferredUserId=%s, personnelNumber=%s, email=%s, getLabel()=%s, getDescription()=%s, getScore()=%s]",
                personId, administrativeTenant, externalId, guid, preferredUserId, personnelNumber, email, getLabel(),
                getDescription(), getScore());
    }

}