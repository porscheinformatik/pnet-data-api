/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api.person;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithPersonId;

/**
 * An result item for a search for persons.
 *
 * @author ham
 */
@ApiModel(description = "Holds basic information about a person")
public class PersonItemDTO implements WithPersonId, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -481025382258675738L;

    @ApiModelProperty(notes = "The unique id of the person.")
    private final Integer personId;

    @ApiModelProperty(notes = "The tenant, in which this person is administrated.")
    private final String administrativeTenant;

    @ApiModelProperty(notes = "The tenants where the person is valid.")
    private final Collection<String> tenants;

    @ApiModelProperty(notes = "The form of the adress the person prefers.")
    private final FormOfAddress formOfAddress;

    @ApiModelProperty(notes = "The academic title of the person.")
    private final String academicTitle;

    @ApiModelProperty(notes = "The academic title of the person, placed after the name.")
    private final String academicTitlePostNominal;

    @ApiModelProperty(notes = "The first name of the person.")
    private final String firstName;

    @ApiModelProperty(notes = "The last name of the person.")
    private final String lastName;

    @ApiModelProperty(notes = "The personnel number of the person.")
    private final String personnelNumber;

    @ApiModelProperty(notes = "The birthdate of the person.")
    private final LocalDate birthdate;

    @ApiModelProperty(notes = " The email of the person.")
    private final String email;

    @ApiModelProperty(notes = "The time and date when the person was last changed.")
    private final LocalDateTime lastUpdate;

    public PersonItemDTO(@JsonProperty("personId") Integer personId,
        @JsonProperty("administrativeTenant") String administrativeTenant,
        @JsonProperty("tenants") Collection<String> tenants, @JsonProperty("formOfAddress") FormOfAddress formOfAddress,
        @JsonProperty("academicTitle") String academicTitle,
        @JsonProperty("academicTitlePostNominal") String academicTitlePostNominal,
        @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
        @JsonProperty("personnelNumber") String personnelNumber, @JsonProperty("birthdate") LocalDate birthdate,
        @JsonProperty("email") String email, @JsonProperty("lastUpdate") LocalDateTime lastUpdate)
    {
        super();

        this.personId = personId;
        this.administrativeTenant = administrativeTenant;
        this.tenants = tenants;
        this.formOfAddress = formOfAddress;
        this.academicTitle = academicTitle;
        this.academicTitlePostNominal = academicTitlePostNominal;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personnelNumber = personnelNumber;
        this.birthdate = birthdate;
        this.email = email;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public Integer getPersonId()
    {
        return personId;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public Collection<String> getTenants()
    {
        return tenants;
    }

    public FormOfAddress getFormOfAddress()
    {
        return formOfAddress;
    }

    public String getAcademicTitle()
    {
        return academicTitle;
    }

    public String getAcademicTitlePostNominal()
    {
        return academicTitlePostNominal;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPersonnelNumber()
    {
        return personnelNumber;
    }

    public LocalDate getBirthdate()
    {
        return birthdate;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public String toString()
    {
        return String
            .format(
                "PersonItemDTO [personId=%s, administrativeTenant=%s, tenants=%s, formOfAddress=%s, academicTitle=%s, "
                    + "academicTitlePostNominal=%s, firstName=%s, lastName=%s, personnelNumber=%s, birthdate=%s, "
                    + "email=%s, lastUpdate=%s]",
                personId, administrativeTenant, tenants, formOfAddress, academicTitle, academicTitlePostNominal,
                firstName, lastName, personnelNumber, birthdate, email, lastUpdate);
    }

}
