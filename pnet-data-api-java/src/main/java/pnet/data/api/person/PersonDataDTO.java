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
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.util.WithId;
import pnet.data.api.util.WithLastUpdate;

/**
 * Holds one person.
 *
 * @author ham
 */
@ApiModel(description = "Holds all information about a person")
public class PersonDataDTO implements WithId, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -2096202204327773391L;

    @ApiModelProperty(notes = "The unique id of the person")
    private final Integer id;

    @ApiModelProperty(notes = "The tenant, in which this person is administrated")
    private String administrativeTenant;

    @ApiModelProperty(notes = "The tenants where the person is valid")
    private Collection<String> tenants;

    @ApiModelProperty(notes = "The form of the adress the person prefers")
    private FormOfAddress formOfAddress;

    @ApiModelProperty(notes = "The academic title of the person")
    private String academicTitle;

    @ApiModelProperty(notes = "The academic title of the person, that's placed after the name.")
    private String academicTitlePostNominal;

    @ApiModelProperty(notes = "The first name of the person")
    private String firstName;

    @ApiModelProperty(notes = "The last name of the person")
    private String lastName;

    @ApiModelProperty(notes = "The birthdate of the person")
    private LocalDate birthdate;

    @ApiModelProperty(notes = "The external id of the person")
    private String externalId;

    @ApiModelProperty(notes = "The global user id of the person")
    private String guid;

    @ApiModelProperty(notes = "The preferred user id of the person")
    private String preferredUserId;

    @ApiModelProperty(notes = "The phone number of the person at work")
    private String phoneNumber;

    @ApiModelProperty(notes = "The mobile phone number of the person regarding business")
    private String mobileNumber;

    @ApiModelProperty(notes = "The fax number of the person at work")
    private String faxNumber;

    @ApiModelProperty(notes = "The business email of the person")
    private String email;

    @ApiModelProperty(notes = "The id of the company the person is mainly busy at")
    private Integer contactCompanyId;

    @ApiModelProperty(notes = "The cost center of the person")
    private String costCenter;

    @ApiModelProperty(notes = "The personnel number of the person")
    private String personnelNumber;

    @ApiModelProperty(notes = "The personnel number of the supervisor of the person")
    private String supervisorPersonnelNumber;

    @ApiModelProperty(notes = "The controlling area the person belongs to")
    private String controllingArea;

    @ApiModelProperty(notes = "The personnel department the person belongs to")
    private String personnelDepartment;

    @ApiModelProperty(notes = "The description of the job the person mainly does")
    private String jobDescription;

    @ApiModelProperty(notes = "The languages known to the user, ordered by preference")
    private List<PersonLanguageLinkDTO> languages;

    @ApiModelProperty(notes = "The companies the person has employments at")
    private Collection<PersonCompanyLinkDTO> companies;

    @ApiModelProperty(notes = "The number types the person has at specific companies")
    private Collection<PersonNumberTypeLinkDTO> numbers;

    @ApiModelProperty(notes = "The functions the person has at specific companies")
    private Collection<PersonFunctionLinkDTO> functions;

    @ApiModelProperty(notes = "The activities the person has at specific companies")
    private Collection<PersonActivityLinkDTO> activities;

    @ApiModelProperty(notes = "The advisor assignments of the person for specific companies")
    private Collection<PersonAdvisorAssignmentLinkDTO> advisorAssignments;

    @ApiModelProperty(notes = "Indicates, whether the person will get deleted automatically in the near future")
    private boolean automaticDeletion;

    @ApiModelProperty(notes = "The checksum of all data of a person, which is needed to detect changes")
    private String checksum;

    @ApiModelProperty(notes = "The time and date when the person was last changed")
    private LocalDateTime lastUpdate;

    public PersonDataDTO(@JsonProperty("id") Integer id)
    {
        super();

        this.id = id;
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public void setAdministrativeTenant(String administrativeTenant)
    {
        this.administrativeTenant = administrativeTenant;
    }

    public Collection<String> getTenants()
    {
        return tenants;
    }

    public void setTenants(Collection<String> tenants)
    {
        this.tenants = tenants;
    }

    public FormOfAddress getFormOfAddress()
    {
        return formOfAddress;
    }

    public void setFormOfAddress(FormOfAddress formOfAddress)
    {
        this.formOfAddress = formOfAddress;
    }

    public String getAcademicTitle()
    {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle)
    {
        this.academicTitle = academicTitle;
    }

    public String getAcademicTitlePostNominal()
    {
        return academicTitlePostNominal;
    }

    public void setAcademicTitlePostNominal(String academicTitlePostNominal)
    {
        this.academicTitlePostNominal = academicTitlePostNominal;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getExternalId()
    {
        return externalId;
    }

    public void setExternalId(String externalId)
    {
        this.externalId = externalId;
    }

    public String getGuid()
    {
        return guid;
    }

    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    public String getPreferredUserId()
    {
        return preferredUserId;
    }

    public void setPreferredUserId(String preferredUserId)
    {
        this.preferredUserId = preferredUserId;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public String getFaxNumber()
    {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber)
    {
        this.faxNumber = faxNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getContactCompanyId()
    {
        return contactCompanyId;
    }

    public void setContactCompanyId(Integer contactCompanyId)
    {
        this.contactCompanyId = contactCompanyId;
    }

    public String getCostCenter()
    {
        return costCenter;
    }

    public void setCostCenter(String costCenter)
    {
        this.costCenter = costCenter;
    }

    public String getPersonnelNumber()
    {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber)
    {
        this.personnelNumber = personnelNumber;
    }

    public String getSupervisorPersonnelNumber()
    {
        return supervisorPersonnelNumber;
    }

    public void setSupervisorPersonnelNumber(String supervisorPersonnelNumber)
    {
        this.supervisorPersonnelNumber = supervisorPersonnelNumber;
    }

    public String getControllingArea()
    {
        return controllingArea;
    }

    public void setControllingArea(String controllingArea)
    {
        this.controllingArea = controllingArea;
    }

    public String getPersonnelDepartment()
    {
        return personnelDepartment;
    }

    public void setPersonnelDepartment(String personnelDepartment)
    {
        this.personnelDepartment = personnelDepartment;
    }

    public String getJobDescription()
    {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription)
    {
        this.jobDescription = jobDescription;
    }

    public List<PersonLanguageLinkDTO> getLanguages()
    {
        return languages;
    }

    public void setLanguages(List<PersonLanguageLinkDTO> languages)
    {
        this.languages = languages;
    }

    public Collection<PersonCompanyLinkDTO> getCompanies()
    {
        return companies;
    }

    public Optional<PersonCompanyLinkDTO> findCompany(Predicate<? super PersonCompanyLinkDTO> predicate)
    {
        return companies.stream().filter(predicate).findFirst();
    }

    public void setCompanies(Collection<PersonCompanyLinkDTO> companies)
    {
        this.companies = companies;
    }

    public Collection<PersonNumberTypeLinkDTO> getNumbers()
    {
        return numbers;
    }

    public Optional<PersonNumberTypeLinkDTO> findNumber(Predicate<? super PersonNumberTypeLinkDTO> predicate)
    {
        return numbers.stream().filter(predicate).findFirst();
    }

    public void setNumbers(Collection<PersonNumberTypeLinkDTO> numbers)
    {
        this.numbers = numbers;
    }

    public Collection<PersonFunctionLinkDTO> getFunctions()
    {
        return functions;
    }

    public Optional<PersonFunctionLinkDTO> findFunction(Predicate<? super PersonFunctionLinkDTO> predicate)
    {
        return functions.stream().filter(predicate).findFirst();
    }

    public void setFunctions(Collection<PersonFunctionLinkDTO> functions)
    {
        this.functions = functions;
    }

    public Collection<PersonActivityLinkDTO> getActivities()
    {
        return activities;
    }

    public Optional<PersonActivityLinkDTO> findActivity(Predicate<? super PersonActivityLinkDTO> predicate)
    {
        return activities.stream().filter(predicate).findFirst();
    }

    public void setActivities(Collection<PersonActivityLinkDTO> activities)
    {
        this.activities = activities;
    }

    public Collection<PersonAdvisorAssignmentLinkDTO> getAdvisorAssignments()
    {
        return advisorAssignments;
    }

    public Optional<PersonAdvisorAssignmentLinkDTO> findAdvisorAssignment(
        Predicate<? super PersonAdvisorAssignmentLinkDTO> predicate)
    {
        return advisorAssignments.stream().filter(predicate).findFirst();
    }

    public void setAdvisorAssignments(Collection<PersonAdvisorAssignmentLinkDTO> advisorAssignments)
    {
        this.advisorAssignments = advisorAssignments;
    }

    public boolean isAutomaticDeletion()
    {
        return automaticDeletion;
    }

    public void setAutomaticDeletion(boolean automaticDeletion)
    {
        this.automaticDeletion = automaticDeletion;
    }

    public String getChecksum()
    {
        return checksum;
    }

    public void setChecksum(String checksum)
    {
        this.checksum = checksum;
    }

    /**
     * @return The date/time of the last update to this item.
     */
    @Override
    public LocalDateTime getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString()
    {
        return String
            .format("PersonDataDTO [id=%s, administrativeTenant=%s, tenants=%s, formOfAddress=%s, academicTitle=%s, "
                + "academicTitlePostNominal=%s, firstName=%s, lastName=%s, birthdate=%s, externalId=%s, guid=%s, "
                + "preferredUserId=%s, phoneNumber=%s, mobileNumber=%s, faxNumber=%s, email=%s, contactCompanyId=%s, "
                + "costCenter=%s, personnelNumber=%s, supervisorPersonnelNumber=%s, controllingArea=%s, "
                + "personnelDepartment=%s, jobDescription=%s, languages=%s, companies=%s, numbers=%s, functions=%s, "
                + "activities=%s, advisorAssignments=%s, automaticDeletion=%s, checksum=%s, lastUpdate=%s]", id,
                administrativeTenant, tenants, formOfAddress, academicTitle, academicTitlePostNominal, firstName,
                lastName, birthdate, externalId, guid, preferredUserId, phoneNumber, mobileNumber, faxNumber, email,
                contactCompanyId, costCenter, personnelNumber, supervisorPersonnelNumber, controllingArea,
                personnelDepartment, jobDescription, languages, companies, numbers, functions, activities,
                advisorAssignments, automaticDeletion, checksum, lastUpdate);
    }

}
