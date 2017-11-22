package pnet.data.api.person;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.tenant.Tenant;
import pnet.data.api.util.WithLastUpdate;

/**
 * An result item for a search for persons.
 *
 * @author ham
 */
public class PersonItemDTO implements WithLastUpdate
{

    private final Integer personId;
    private final Tenant administrativeTenant;
    private final FormOfAddress formOfAddress;
    private final String academicTitle;
    private final String firstName;
    private final String lastName;
    private final NameAffix nameAffix;
    private final LocalDateTime lastUpdate;

    public PersonItemDTO(@JsonProperty("personId") Integer personId,
        @JsonProperty("administrativeTenant") Tenant administrativeTenant,
        @JsonProperty("formOfAddress") FormOfAddress formOfAddress, @JsonProperty("academicTitle") String academicTitle,
        @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
        @JsonProperty("nameAffix") NameAffix nameAffix, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.personId = personId;
        this.administrativeTenant = administrativeTenant;
        this.formOfAddress = formOfAddress;
        this.academicTitle = academicTitle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameAffix = nameAffix;
        this.lastUpdate = lastUpdate;
    }

    public Integer getPersonId()
    {
        return personId;
    }

    public Tenant getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public FormOfAddress getFormOfAddress()
    {
        return formOfAddress;
    }

    public String getAcademicTitle()
    {
        return academicTitle;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public NameAffix getNameAffix()
    {
        return nameAffix;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String.format(
            "PersonItemDTO [personId=%s, administrativeTenant=%s, formOfAddress=%s, academicTitle=%s, firstName=%s, lastName=%s, nameAffix=%s, lastUpdate=%s]",
            personId, administrativeTenant, formOfAddress, academicTitle, firstName, lastName, nameAffix, lastUpdate);
    }

}
