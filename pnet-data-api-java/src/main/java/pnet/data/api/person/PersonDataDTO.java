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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import pnet.data.api.util.ApprovalState;
import pnet.data.api.util.WithId;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithPersonId;
import pnet.data.api.util.WithTenants;

/**
 * Holds one person.
 *
 * @author ham
 */
@SuppressWarnings("deprecation")
@Schema(description = "Holds all information about a person")
public class PersonDataDTO implements WithId, WithPersonId, WithTenants, WithLastUpdate, Serializable {

    @Serial
    private static final long serialVersionUID = -2096202204327773391L;

    @Schema(description = "The unique id of the person (needed scope: SC_IDENTIFIER).")
    @Deprecated
    private final Integer id;

    @Schema(description = "The unique id of the person (needed scope: SC_IDENTIFIER).")
    private final Integer personId;

    @Schema(description = "The tenant, in which this person is administrated (no scope needed).")
    private String administrativeTenant;

    @Schema(description = "The tenants where the person is valid (no scope needed).")
    private Collection<String> tenants;

    @Schema(description = "The type of user (no scope needed).")
    private PersonType type;

    @Schema(description = "The form of the adress the person prefers (needed scope: SC_GENDER).")
    private FormOfAddress formOfAddress;

    @Schema(description = "The academic title of the person (needed scope: SC_NAME).")
    private String academicTitle;

    @Schema(description = "The academic title of the person, that's placed after the name (needed scope: SC_NAME).")
    private String academicTitlePostNominal;

    @Schema(description = "The first name of the person (needed scope: SC_NAME).")
    private String firstName;

    @Schema(description = "The last name of the person (needed scope: SC_NAME).")
    private String lastName;

    @Schema(description = "The username of the person (needed scope: SC_PNET_ACCOUNT).")
    private String username;

    @Schema(description = "The tax number of the person (needed scope: SC_IDENTIFIER). Only availible in some countries")
    private String taxNumber;

    @Schema(description = "The person is able to access the Partner.Net (needed scope: SC_PNET_ACCOUNT).")
    private Boolean credentialsAvailable;

    @Schema(description = "True, if the user has (or had) additional authentication factors enabled.")
    private Boolean multifactorEnabled;

    @Schema(
        description = "True, if the person has been fully approved by authorities, false if the approval process is still " +
        "ongoing (needed scope: SC_APPROVAL_PROCESS). This property is never null. If the scope is missing, " +
        "only approved persons will be available. NOTE: Person approvals are deprecated as of PNETREQ-1574. " +
        "The value will always be set to 'true'."
    )
    @Deprecated(since = "22.01.2024")
    private boolean approved;

    @Schema(
        description = "The current state of the audit process. NOTE: Person approvals are deprecated as of PNETREQ-1574. " +
        "The value will always be set to 'ApprovalState.DONE'."
    )
    @Deprecated(since = "22.01.2024")
    private ApprovalState approvalState;

    @Schema(description = "The birthdate of the person (needed scope: SC_BIRTHDATE).")
    private LocalDate birthdate;

    @Schema(description = "The external id of the person (needed scope: SC_IDENTIFIER).")
    private String externalId;

    @Schema(description = "The global user id of the person (needed scope: SC_IDENTIFIER).")
    private String guid;

    @Schema(description = "The preferred user id of the person (needed scope: SC_IDENTIFIER).")
    private String preferredUserId;

    @Schema(description = "The phone number of the person at work (needed scope: SC_PHONE_NUMBER).")
    private String phoneNumber;

    @Schema(description = "The extension number of the person at work (needed scope: SC_PHONE_NUMBER).")
    private String extensionNumber;

    @Schema(description = "The mobile phone number of the person regarding business (needed scope: SC_PHONE_NUMBER).")
    private String mobileNumber;

    @Schema(description = "The fax number of the person at work (needed scope: SC_PHONE_NUMBER).")
    private String faxNumber;

    @Schema(description = "The business email of the person (needed scope: SC_EMAIL).")
    private String email;

    @Schema(description = "The id of the company the person is mainly busy at (needed scope: SC_PREFERRED_COMPANY).")
    private Integer contactCompanyId;

    @Schema(
        description = "The matchcode of the company the person is mainly busy at (needed scope: " +
        "SC_PREFERRED_COMPANY)."
    )
    private String contactCompanyMatchcode;

    @Schema(
        description = "The number of the company the person is mainly busy at (needed scope: " +
        "SC_PREFERRED_COMPANY)."
    )
    private String contactCompanyNumber;

    @Schema(description = "The cost center of the person (needed scope: SC_ORGANIZATION_UNIT).")
    private String costCenter;

    @Schema(description = "The personnel number of the person (needed scope: SC_ORGANIZATION_UNIT).")
    private String personnelNumber;

    @Schema(description = "The personnel number of the supervisor of the person (needed scope: SC_ORGANIZATION_UNIT).")
    private String supervisorPersonnelNumber;

    @Schema(description = "The controlling area the person belongs to (needed scope: SC_ORGANIZATION_UNIT).")
    private String controllingArea;

    @Schema(description = "The personnel department the person belongs to (needed scope: SC_ORGANIZATION_UNIT).")
    private String personnelDepartment;

    @Schema(description = "The description of the job the person mainly does (needed scope: SC_ORGANIZATION_UNIT).")
    private String jobDescription;

    @Schema(description = "The matchcode of the team the person is part of (needed scope: SC_ORGANIZATION_UNIT).")
    private String teamMatchcode;

    @Schema(description = "The name of the team the person is part of (needed scope: SC_ORGANIZATION_UNIT).")
    private String teamLabel;

    @Schema(description = "The explicit login locks of the person (needed scope: SC_PERSON_LOCKS).")
    private Collection<PersonLockLinkDTO> personLocks;

    @Schema(description = "Indicates whether the user account is locked, preventing the user from logging in via Partner.Net. Additional details about the lock reason can be found in the `personLocks` field. (needed scope: SC_IDENTIFIER).")
    private Boolean locked;

    @Schema(description = "The tenant specific settings of the user (needed scope: SC_PREFERRED_COMPANY).")
    private Collection<PersonSettingsLinkDTO> settings;

    @Schema(description = "The languages known to the user, ordered by preference (needed scope: SC_LANGUAGE).")
    private List<PersonLanguageLinkDTO> languages;

    @Schema(description = "The companies the person has employments at (needed scope: SC_EMPLOYMENT).")
    private Collection<PersonCompanyLinkDTO> companies;

    @Schema(description = "The number types the person has at specific companies (needed scope: SC_IDENTIFIER).")
    private Collection<PersonNumberTypeLinkDTO> numbers;

    @Schema(description = "The functions the person has at specific companies (needed scope: SC_ROLE).")
    private Collection<PersonFunctionLinkDTO> functions;

    @Schema(description = "The activities the person has at specific companies (needed scope: SC_ROLE).")
    private Collection<PersonActivityLinkDTO> activities;

    @Schema(
        description = "The advisor assignments of the person for specific companies (needed scope: " +
        "SC_ADVISOR_ASSIGNMENT)."
    )
    private Collection<PersonAdvisorAssignmentLinkDTO> advisorAssignments;

    @Schema(
        description = "The hierarchy of persons, e.g. responsible persons for bots and test users (needed scope: SC_HIERARCHY)."
    )
    private List<PersonHierarchyLinkDTO> hierarchies;

    @Schema(description = "Indicates, whether the person has a portrait available or not (needed scope: SC_IMAGE).")
    private Boolean portraitAvailable;

    @Schema(
        description = "Indicates, whether the person will get deleted automatically in the near future (no scope " +
        "needed)."
    )
    private boolean automaticDeletion;

    @Schema(description = "The checksum of all data of a person, which is needed to detect changes (no scope needed).")
    private String checksum;

    @Schema(description = "The time and date when the person was last changed (no scope needed).")
    private LocalDateTime lastUpdate;

    @Schema(description = "The date, when the user needs to be recertified the next time")
    private LocalDateTime recertValidTo;

    public PersonDataDTO(@JsonProperty("id") Integer id, @JsonProperty("personId") Integer personId) {
        super();
        this.id = id != null ? id : personId;
        this.personId = personId != null ? personId : id;
    }

    /**
     * Returns the person id
     *
     * @return person id
     * @deprecated use {@link #getPersonId()} instead
     */
    @Override
    @Deprecated
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getPersonId() {
        return personId;
    }

    public String getAdministrativeTenant() {
        return administrativeTenant;
    }

    public void setAdministrativeTenant(String administrativeTenant) {
        this.administrativeTenant = administrativeTenant;
    }

    @Override
    public Collection<String> getTenants() {
        return tenants;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public void setTenants(Collection<String> tenants) {
        this.tenants = tenants;
    }

    public FormOfAddress getFormOfAddress() {
        return formOfAddress;
    }

    public void setFormOfAddress(FormOfAddress formOfAddress) {
        this.formOfAddress = formOfAddress;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getAcademicTitlePostNominal() {
        return academicTitlePostNominal;
    }

    public void setAcademicTitlePostNominal(String academicTitlePostNominal) {
        this.academicTitlePostNominal = academicTitlePostNominal;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Boolean getCredentialsAvailable() {
        return credentialsAvailable;
    }

    public void setCredentialsAvailable(Boolean credentialsAvailable) {
        this.credentialsAvailable = credentialsAvailable;
    }

    public Boolean getMultifactorEnabled() {
        return multifactorEnabled;
    }

    public void setMultifactorEnabled(Boolean multifactorEnabled) {
        this.multifactorEnabled = multifactorEnabled;
    }

    @Deprecated(since = "22.01.2024")
    public boolean isApproved() {
        return approved;
    }

    @Deprecated(since = "22.01.2024")
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Deprecated(since = "22.01.2024")
    public ApprovalState getApprovalState() {
        return approvalState;
    }

    @Deprecated(since = "22.01.2024")
    public void setApprovalState(ApprovalState approvalState) {
        this.approvalState = approvalState;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPreferredUserId() {
        return preferredUserId;
    }

    public void setPreferredUserId(String preferredUserId) {
        this.preferredUserId = preferredUserId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContactCompanyId() {
        return contactCompanyId;
    }

    public void setContactCompanyId(Integer contactCompanyId) {
        this.contactCompanyId = contactCompanyId;
    }

    public String getContactCompanyMatchcode() {
        return contactCompanyMatchcode;
    }

    public void setContactCompanyMatchcode(String contactCompanyMatchcode) {
        this.contactCompanyMatchcode = contactCompanyMatchcode;
    }

    public String getContactCompanyNumber() {
        return contactCompanyNumber;
    }

    public void setContactCompanyNumber(String contactCompanyNumber) {
        this.contactCompanyNumber = contactCompanyNumber;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getSupervisorPersonnelNumber() {
        return supervisorPersonnelNumber;
    }

    public void setSupervisorPersonnelNumber(String supervisorPersonnelNumber) {
        this.supervisorPersonnelNumber = supervisorPersonnelNumber;
    }

    public String getControllingArea() {
        return controllingArea;
    }

    public void setControllingArea(String controllingArea) {
        this.controllingArea = controllingArea;
    }

    public String getPersonnelDepartment() {
        return personnelDepartment;
    }

    public void setPersonnelDepartment(String personnelDepartment) {
        this.personnelDepartment = personnelDepartment;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getTeamMatchcode() {
        return teamMatchcode;
    }

    public void setTeamMatchcode(String teamMatchcode) {
        this.teamMatchcode = teamMatchcode;
    }

    public String getTeamLabel() {
        return teamLabel;
    }

    public void setTeamLabel(String teamLabel) {
        this.teamLabel = teamLabel;
    }

    public Collection<PersonLockLinkDTO> getPersonLocks() {
        return personLocks;
    }

    public void setPersonLocks(Collection<PersonLockLinkDTO> personLocks) {
        this.personLocks = personLocks;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

        /**
     * @deprecated since 2.11.7, use {@link #getLocked()} instead.
     */
    @Deprecated(since = "2.11.7", forRemoval = false)
    public Boolean isLocked() {
        return locked;
    }

    public Boolean getLocked() {
        return locked;
    }

    public Collection<PersonSettingsLinkDTO> getSettings() {
        return settings;
    }

    public Optional<PersonSettingsLinkDTO> findSettings(Predicate<? super PersonSettingsLinkDTO> predicate) {
        return settings == null ? Optional.empty() : settings.stream().filter(predicate).findFirst();
    }

    public void setSettings(Collection<PersonSettingsLinkDTO> settings) {
        this.settings = settings;
    }

    public List<PersonLanguageLinkDTO> getLanguages() {
        return languages;
    }

    public Optional<PersonLanguageLinkDTO> findLanguage(Predicate<? super PersonLanguageLinkDTO> predicate) {
        return languages == null ? Optional.empty() : languages.stream().filter(predicate).findFirst();
    }

    public void setLanguages(List<PersonLanguageLinkDTO> languages) {
        this.languages = languages;
    }

    public Collection<PersonCompanyLinkDTO> getCompanies() {
        return companies;
    }

    public Optional<PersonCompanyLinkDTO> findCompany(Predicate<? super PersonCompanyLinkDTO> predicate) {
        return companies == null ? Optional.empty() : companies.stream().filter(predicate).findFirst();
    }

    public void setCompanies(Collection<PersonCompanyLinkDTO> companies) {
        this.companies = companies;
    }

    public Collection<PersonNumberTypeLinkDTO> getNumbers() {
        return numbers;
    }

    public Optional<PersonNumberTypeLinkDTO> findNumber(Predicate<? super PersonNumberTypeLinkDTO> predicate) {
        return numbers == null ? Optional.empty() : numbers.stream().filter(predicate).findFirst();
    }

    public void setNumbers(Collection<PersonNumberTypeLinkDTO> numbers) {
        this.numbers = numbers;
    }

    public Collection<PersonFunctionLinkDTO> getFunctions() {
        return functions;
    }

    public Optional<PersonFunctionLinkDTO> findFunction(Predicate<? super PersonFunctionLinkDTO> predicate) {
        return functions == null ? Optional.empty() : functions.stream().filter(predicate).findFirst();
    }

    public void setFunctions(Collection<PersonFunctionLinkDTO> functions) {
        this.functions = functions;
    }

    public Collection<PersonActivityLinkDTO> getActivities() {
        return activities;
    }

    public Optional<PersonActivityLinkDTO> findActivity(Predicate<? super PersonActivityLinkDTO> predicate) {
        return activities == null ? Optional.empty() : activities.stream().filter(predicate).findFirst();
    }

    public void setActivities(Collection<PersonActivityLinkDTO> activities) {
        this.activities = activities;
    }

    public Collection<PersonAdvisorAssignmentLinkDTO> getAdvisorAssignments() {
        return advisorAssignments;
    }

    public Optional<PersonAdvisorAssignmentLinkDTO> findAdvisorAssignment(
        Predicate<? super PersonAdvisorAssignmentLinkDTO> predicate
    ) {
        return advisorAssignments == null
            ? Optional.empty()
            : advisorAssignments.stream().filter(predicate).findFirst();
    }

    public void setAdvisorAssignments(Collection<PersonAdvisorAssignmentLinkDTO> advisorAssignments) {
        this.advisorAssignments = advisorAssignments;
    }

    public List<PersonHierarchyLinkDTO> getHierarchies() {
        return hierarchies;
    }

    public Optional<PersonHierarchyLinkDTO> findHierarchy(Predicate<? super PersonHierarchyLinkDTO> predicate) {
        return hierarchies == null ? Optional.empty() : hierarchies.stream().filter(predicate).findFirst();
    }

    public void setHierarchies(List<PersonHierarchyLinkDTO> hierarchies) {
        this.hierarchies = hierarchies;
    }

    public boolean isAutomaticDeletion() {
        return automaticDeletion;
    }

    public void setAutomaticDeletion(boolean automaticDeletion) {
        this.automaticDeletion = automaticDeletion;
    }

    public Boolean getPortraitAvailable() {
        return portraitAvailable;
    }

    public void setPortraitAvailable(Boolean portraitAvailable) {
        this.portraitAvailable = portraitAvailable;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    /**
     * @return The date/time of the last update to this item.
     */
    @Override
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getRecertValidTo() {
        return recertValidTo;
    }

    public void setRecertValidTo(LocalDateTime recertValidTo) {
        this.recertValidTo = recertValidTo;
    }

    @Override
    public String toString() {
        return String.format(
            "PersonDataDTO [personId=%s, administrativeTenant=%s, tenants=%s, type=%s, formOfAddress=%s, " +
            "academicTitle=%s, academicTitlePostNominal=%s, firstName=%s, lastName=%s, credentialsAvailable=%s, " +
            "multifactorEnabled=%s, approved=%s, approvalState=%s, birthdate=%s, externalId=%s, guid=%s, " +
            "preferredUserId=%s, phoneNumber=%s, extensionNumber=%s, mobileNumber=%s, faxNumber=%s, email=%s, " +
            "contactCompanyId=%s, contactCompanyMatchcode=%s, contactCompanyNumber=%s, costCenter=%s, " +
            "personnelNumber=%s, supervisorPersonnelNumber=%s, controllingArea=%s, personnelDepartment=%s, " +
            "jobDescription=%s, teamMatchcode=%s, teamLabel=%s, personLocks=%s, isLocked=%s, settings=%s, languages=%s, companies=%s, " +
            "numbers=%s, functions=%s, activities=%s, advisorAssignments=%s, hierarchies=%s, " +
            "portraitAvailable=%s, automaticDeletion=%s, checksum=%s, lastUpdate=%s, taxNumber=%s," +
            "recertValidTo=%s]",
            personId,
            administrativeTenant,
            tenants,
            type,
            formOfAddress,
            academicTitle,
            academicTitlePostNominal,
            firstName,
            lastName,
            credentialsAvailable,
            multifactorEnabled,
            approved,
            approvalState,
            birthdate,
            externalId,
            guid,
            preferredUserId,
            phoneNumber,
            extensionNumber,
            mobileNumber,
            faxNumber,
            email,
            contactCompanyId,
            contactCompanyMatchcode,
            contactCompanyNumber,
            costCenter,
            personnelNumber,
            supervisorPersonnelNumber,
            controllingArea,
            personnelDepartment,
            jobDescription,
            teamMatchcode,
            teamLabel,
            personLocks,
            locked,
            settings,
            languages,
            companies,
            numbers,
            functions,
            activities,
            advisorAssignments,
            hierarchies,
            portraitAvailable,
            automaticDeletion,
            checksum,
            lastUpdate,
            taxNumber,
            recertValidTo
        );
    }
}
