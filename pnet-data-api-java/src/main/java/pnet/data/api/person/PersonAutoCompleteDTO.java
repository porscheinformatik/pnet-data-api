package pnet.data.api.person;

import java.io.Serial;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import pnet.data.api.util.AbstractAutoCompleteDTO;

/**
 * Holds the result of an auto complete query.
 *
 * @author HAM
 */
public class PersonAutoCompleteDTO extends AbstractAutoCompleteDTO
{
    @Serial
    private static final long serialVersionUID = -6275336068143194073L;

    @Schema(description = "The unique id of the person.")
    private final Integer personId;

    @Schema(description = "The tenant, in which this person is administrated.")
    private final String administrativeTenant;

    @Schema(description = "The type of user (no scope needed).")
    private final PersonType type;

    @Schema(description = "The external id of the person.")
    private final String externalId;

    @Schema(description = "The global user id of the person.")
    private final String guid;

    @Schema(description = "The preferred user id of the person.")
    private final String preferredUserId;

    @Schema(description = "The personnel number of the person.")
    private final String personnelNumber;

    @Schema(description = "The email of the person.")
    private final String email;

    @SuppressWarnings("java:S107")
    public PersonAutoCompleteDTO(@JsonProperty("personId") Integer personId,
        @JsonProperty("administrativeTenant") String administrativeTenant, @JsonProperty("type") PersonType type,
        @JsonProperty("label") String label, @JsonProperty("description") String description,
        @JsonProperty("externalId") String externalId, @JsonProperty("guid") String guid,
        @JsonProperty("preferredUserId") String preferredUserId,
        @JsonProperty("personnelNumber") String personnelNumber, @JsonProperty("email") String email,
        @JsonProperty("score") double score)
    {
        super(label, description, score);

        this.personId = personId;
        this.administrativeTenant = administrativeTenant;
        this.type = type;
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

    public PersonType getType()
    {
        return type;
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
        return String.format("PersonAutoCompleteDTO [personId=%s, administrativeTenant=%s, type=%s, externalId=%s, "
                + "guid=%s, preferredUserId=%s, personnelNumber=%s, email=%s, getLabel()=%s, getDescription()=%s, "
                + "getScore()=%s]",
            personId, administrativeTenant, type, externalId, guid, preferredUserId, personnelNumber, email, getLabel(),
            getDescription(), getScore());
    }

}
