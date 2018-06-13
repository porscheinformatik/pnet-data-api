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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithId;
import pnet.data.api.util.WithLastUpdate;

/**
 * Holds one person.
 *
 * @author ham
 */
public class PersonDataDTO implements WithId, WithLastUpdate, Serializable
{

    private static final long serialVersionUID = -2096202204327773391L;

    private final Integer id;

    private String administrativeTenant;
    private Collection<String> tenants;
    private FormOfAddress formOfAddress;
    private String academicTitle;
    private String firstName;
    private String lastName;
    private NameAffix nameAffix;
    private String guid;
    private String preferredUserId;
    private String phoneNumber;
    private String mobileNumber;
    private String faxNumber;
    private String email;
    private Integer contactCompanyId;
    private String costCenter;
    private String personnelNumber;
    private String supervisorPersonnelNumber;
    private String controllingArea;
    private String personnelDepartment;
    private String jobDescription;
    private Collection<PersonCompanyLinkDTO> companies;
    private Collection<PersonNumberTypeLinkDTO> numbers;
    private Collection<PersonFunctionLinkDTO> functions;
    private Collection<PersonActivityLinkDTO> activities;
    private String checksum;
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

    public NameAffix getNameAffix()
    {
        return nameAffix;
    }

    public void setNameAffix(NameAffix nameAffix)
    {
        this.nameAffix = nameAffix;
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
        return String.format(
            "PersonDataDTO [id=%s, administrativeTenant=%s, tenants=%s, formOfAddress=%s, academicTitle=%s, "
                + "firstName=%s, lastName=%s, nameAffix=%s, guid=%s, preferredUserId=%s, phoneNumber=%s, mobileNumber=%s, "
                + "faxNumber=%s, email=%s, contactCompanyId=%s, costCenter=%s, personnelNumber=%s, "
                + "supervisorPersonnelNumber=%s, controllingArea=%s, personnelDepartment=%s, jobDescription=%s, companies=%s, "
                + "numbers=%s, functions=%s, activities=%s, checksum=%s, lastUpdate=%s]",
            id, administrativeTenant, tenants, formOfAddress, academicTitle, firstName, lastName, nameAffix, guid,
            preferredUserId, phoneNumber, mobileNumber, faxNumber, email, contactCompanyId, costCenter, personnelNumber,
            supervisorPersonnelNumber, controllingArea, personnelDepartment, jobDescription, companies, numbers,
            functions, activities, checksum, lastUpdate);
    }

}
