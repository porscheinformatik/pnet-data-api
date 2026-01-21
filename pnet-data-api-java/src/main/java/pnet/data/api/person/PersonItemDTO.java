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
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import pnet.data.api.util.ApprovalState;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithPersonId;
import pnet.data.api.util.WithScore;
import pnet.data.api.util.WithTenants;

/**
 * An result item for a search for persons.
 *
 * @author ham
 */
@Schema(description = "Holds basic information about a person")
public class PersonItemDTO implements WithPersonId, WithTenants, WithLastUpdate, WithScore, Serializable {

    @Serial
    private static final long serialVersionUID = -481025382258675738L;

    @Schema(description = "The unique id of the person (needed scope: SC_IDENTIFIER).")
    private final Integer personId;

    @Schema(description = "The tenant, in which this person is administrated (no scope needed).")
    private final String administrativeTenant;

    @Schema(description = "The tenants where the person is valid (no scope needed).")
    private final Collection<String> tenants;

    @Schema(description = "The type of user (no scope needed).")
    private final PersonType type;

    @Schema(description = "The form of the address the person prefers (needed scope: SC_GENDER).")
    private final FormOfAddress formOfAddress;

    @Schema(description = "The academic title of the person (needed scope: SC_NAME).")
    private final String academicTitle;

    @Schema(description = "The academic title of the person, placed after the name (needed scope: SC_NAME).")
    private final String academicTitlePostNominal;

    @Schema(description = "The first name of the person (needed scope: SC_NAME).")
    private final String firstName;

    @Schema(description = "The last name of the person (needed scope: SC_NAME).")
    private final String lastName;

    @Schema(description = "The username of the person (needed scope: SC_PNET_ACCOUNT).")
    private final String username;

    @Schema(description = "The tax number of the person (needed scope: SC_IDENTIFIER). Only availible in some countries")
    private String taxNumber;

    @Schema(description = "The bdoId references the user-id in BDO (needed scope: SC_IDENTIFIER).")
    private String bdoId;

    @Schema(description = "The username of the person (needed scope: SC_PNET_ACCOUNT).")
    private final Boolean credentialsAvailable;

    @Schema(description = "True, if the user has (or had) additional authentication factors enabled.")
    private final Boolean multifactorEnabled;

    @Schema(
        description = "True, if the person has been fully approved by authorities, false if the approval process is still " +
        "ongoing (needed scope: SC_APPROVAL_PROCESS). This property is never null. If the scope is missing, " +
        "only approved persons will be available. NOTE: Person approvals are deprecated as of PNETREQ-1574. The" +
        " value " +
        "will always be set to 'true'."
    )
    @Deprecated(since = "22.01.2024")
    private final boolean approved;

    @Schema(
        description = "The current state of the audit process. NOTE: Person approvals are deprecated as of PNETREQ-1574. " +
        "The value will always be set to 'ApprovalState.DONE'."
    )
    @Deprecated(since = "22.01.2024")
    private final ApprovalState approvalState;

    @Schema(description = "The external id of the person (needed scope: SC_IDENTIFIER).")
    private final String externalId;

    @Schema(description = "The global user id of the person (needed scope: SC_IDENTIFIER).")
    private final String guid;

    @Schema(description = "The preferred user id of the person (needed scope: SC_IDENTIFIER).")
    private final String preferredUserId;

    @Schema(description = "The personnel number of the person (needed scope: SC_IDENTIFIER).")
    private final String personnelNumber;

    @Schema(description = "The birthdate of the person (needed scope: SC_BIRTHDATE).")
    private final LocalDate birthdate;

    @Schema(description = "The email of the person (needed scope: SC_EMAIL).")
    private final String email;

    @Schema(description = "The phone number of the person (needed scope: SC_PHONE_NUMBER).")
    private final String phoneNumber;

    @Schema(description = "The mobile phone number of the person (needed scope: SC_PHONE_NUMBER).")
    private final String mobileNumber;

    @Schema(
        description = "Indicates whether the user account is locked, preventing the user from logging in via Partner.Net. (needed scope: SC_IDENTIFIER)."
    )
    private Boolean locked;

    @Schema(description = "The languages the person speaks (needed scope: SC_LANGUAGE).")
    private final Collection<Locale> languages;

    @Schema(description = "The companies of the persons (needed scope: SC_EMPLOYMENT).")
    private final Collection<ActivePersonCompanyLinkDTO> companies;

    @Schema(description = "All main functions of the person (needed scope: SC_ROLE).")
    private final Collection<ActivePersonFunctionLinkDTO> functions;

    @Schema(description = "The number types the person has at specific companies (needed scope: SC_IDENTIFIER).")
    private final Collection<ActivePersonNumberTypeLinkDTO> numbers;

    @Schema(description = "The id of the company the person is mainly busy at (needed scope: SC_PREFERRED_COMPANY).")
    private final Integer contactCompanyId;

    @Schema(
        description = "The matchcode of the company the person is mainly busy at (needed scope: " +
        "SC_PREFERRED_COMPANY)."
    )
    private final String contactCompanyMatchcode;

    @Schema(
        description = "The number of the company the person is mainly busy at (needed scope: " +
        "SC_PREFERRED_COMPANY)."
    )
    private final String contactCompanyNumber;

    @Schema(description = "Indicates, whether the person has a portrait available or not (needed scope: SC_IMAGE).")
    private final Boolean portraitAvailable;

    @Schema(description = "The UUID of the portrait of the person (needed scope: SC_IMAGE).")
    private final String portraitUuid;

    @Schema(description = "The UUID of the portrait thumbnail of the person(needed scope: SC_IMAGE).")
    private final String portraitThumbnailUuid;

    @Schema(description = "The time and date when this item has been changed recently (no scope needed).")
    private final LocalDateTime lastUpdate;

    @Schema(description = "The score this item accomplished in the search operation (no scope needed).")
    private final double score;

    @Schema(description = "The date, when the user needs to be recertified the next time")
    private LocalDateTime recertValidTo;

    @SuppressWarnings("java:S107")
    public PersonItemDTO(
        @JsonProperty("personId") Integer personId,
        @JsonProperty("administrativeTenant") String administrativeTenant,
        @JsonProperty("tenants") Collection<String> tenants,
        @JsonProperty("type") PersonType type,
        @JsonProperty("formOfAddress") FormOfAddress formOfAddress,
        @JsonProperty("academicTitle") String academicTitle,
        @JsonProperty("academicTitlePostNominal") String academicTitlePostNominal,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("username") String username,
        @JsonProperty("credentialsAvailable") Boolean credentialsAvailable,
        @JsonProperty("multifactorEnabled") Boolean multifactorEnabled,
        @JsonProperty("approved") @Deprecated(since = "22.01.2024") boolean approved,
        @JsonProperty("approvalState") @Deprecated(since = "22.01.2024") ApprovalState approvalState,
        @JsonProperty("externalId") String externalId,
        @JsonProperty("guid") String guid,
        @JsonProperty("preferredUserId") String preferredUserId,
        @JsonProperty("personnelNumber") String personnelNumber,
        @JsonProperty("birthdate") LocalDate birthdate,
        @JsonProperty("email") String email,
        @JsonProperty("phoneNumber") String phoneNumber,
        @JsonProperty("mobileNumber") String mobileNumber,
        @JsonProperty("locked") Boolean locked,
        @JsonProperty("languages") Collection<Locale> languages,
        @JsonProperty("companies") Collection<ActivePersonCompanyLinkDTO> companies,
        @JsonProperty("functions") Collection<ActivePersonFunctionLinkDTO> functions,
        @JsonProperty("numbers") Collection<ActivePersonNumberTypeLinkDTO> numbers,
        @JsonProperty("contactCompanyId") Integer contactCompanyId,
        @JsonProperty("contactCompanyMatchcode") String contactCompanyMatchcode,
        @JsonProperty("contactCompanyNumber") String contactCompanyNumber,
        @JsonProperty("portraitAvailable") Boolean portraitAvailable,
        @JsonProperty("portraitUuid") String portraitUuid,
        @JsonProperty("portraitThumbnailUuid") String portraitThumbnailUuid,
        @JsonProperty("lastUpdate") LocalDateTime lastUpdate,
        @JsonProperty("score") double score,
        @JsonProperty("taxNumber") String taxNumber,
        @JsonProperty("bdoId") String bdoId,
        @JsonProperty("recertValidTo") LocalDateTime recertValidTo
    ) {
        super();
        this.personId = personId;
        this.administrativeTenant = administrativeTenant;
        this.tenants = tenants;
        this.type = type;
        this.formOfAddress = formOfAddress;
        this.academicTitle = academicTitle;
        this.academicTitlePostNominal = academicTitlePostNominal;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.credentialsAvailable = credentialsAvailable;
        this.multifactorEnabled = multifactorEnabled;
        this.approved = approved;
        this.approvalState = approvalState;
        this.externalId = externalId;
        this.guid = guid;
        this.preferredUserId = preferredUserId;
        this.personnelNumber = personnelNumber;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.locked = locked;
        this.languages = languages;
        this.companies = companies;
        this.functions = functions;
        this.numbers = numbers;
        this.contactCompanyId = contactCompanyId;
        this.contactCompanyMatchcode = contactCompanyMatchcode;
        this.contactCompanyNumber = contactCompanyNumber;
        this.portraitAvailable = portraitAvailable;
        this.portraitUuid = portraitUuid;
        this.portraitThumbnailUuid = portraitThumbnailUuid;
        this.lastUpdate = lastUpdate;
        this.score = score;
        this.taxNumber = taxNumber;
        this.bdoId = bdoId;
        this.recertValidTo = recertValidTo;
    }

    @Override
    public Integer getPersonId() {
        return personId;
    }

    public String getAdministrativeTenant() {
        return administrativeTenant;
    }

    @Override
    public Collection<String> getTenants() {
        return tenants;
    }

    public PersonType getType() {
        return type;
    }

    public FormOfAddress getFormOfAddress() {
        return formOfAddress;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public String getAcademicTitlePostNominal() {
        return academicTitlePostNominal;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getBdoId()
    {
        return bdoId;
    }

    public void setBdoId(String bdoId)
    {
        this.bdoId = bdoId;
    }

    public Boolean getCredentialsAvailable() {
        return credentialsAvailable;
    }

    public Boolean getMultifactorEnabled() {
        return multifactorEnabled;
    }

    @Deprecated(since = "22.01.2024")
    public boolean isApproved() {
        return approved;
    }

    @Deprecated(since = "22.01.2024")
    public ApprovalState getApprovalState() {
        return approvalState;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getGuid() {
        return guid;
    }

    public String getPreferredUserId() {
        return preferredUserId;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
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

    public Collection<Locale> getLanguages() {
        return languages;
    }

    public Collection<ActivePersonCompanyLinkDTO> getCompanies() {
        return companies;
    }

    public Optional<ActivePersonCompanyLinkDTO> findCompany(Predicate<? super ActivePersonCompanyLinkDTO> predicate) {
        return companies == null ? Optional.empty() : companies.stream().filter(predicate).findFirst();
    }

    public Collection<ActivePersonFunctionLinkDTO> getFunctions() {
        return functions;
    }

    public Optional<ActivePersonFunctionLinkDTO> findFunction(
        Predicate<? super ActivePersonFunctionLinkDTO> predicate
    ) {
        return functions == null ? Optional.empty() : functions.stream().filter(predicate).findFirst();
    }

    public Collection<ActivePersonNumberTypeLinkDTO> getNumbers() {
        return numbers;
    }

    public Optional<ActivePersonNumberTypeLinkDTO> findNumber(
        Predicate<? super ActivePersonNumberTypeLinkDTO> predicate
    ) {
        return numbers == null ? Optional.empty() : numbers.stream().filter(predicate).findFirst();
    }

    public Integer getContactCompanyId() {
        return contactCompanyId;
    }

    public String getContactCompanyMatchcode() {
        return contactCompanyMatchcode;
    }

    public String getContactCompanyNumber() {
        return contactCompanyNumber;
    }

    public Boolean getPortraitAvailable() {
        return portraitAvailable;
    }

    public String getPortraitUuid() {
        return portraitUuid;
    }
    
    public String getPortraitThumbnailUuid() {
        return portraitThumbnailUuid;
    }

    @Override
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public double getScore() {
        return score;
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
            "PersonItemDTO [personId=%s, administrativeTenant=%s, tenants=%s, type=%s, formOfAddress=%s, " +
            "academicTitle=%s, academicTitlePostNominal=%s, firstName=%s, lastName=%s, username=%s, " +
            "credentialsAvailable=%s, approved=%s, approvalState=%s, externalId=%s, guid=%s, preferredUserId=%s, " +
            "personnelNumber=%s, birthdate=%s, email=%s, phoneNumber=%s, mobileNumber=%s, locked=%s, languages=%s, " +
            "companies=%s, functions=%s, numbers=%s, contactCompanyId=%s, contactCompanyMatchcode=%s, " +
            "contactCompanyNumber=%s, portraitAvailable=%s, portraitUuid=%s, portraitThumbnailUuid=%s, lastUpdate=%s, score=%s, taxNumber=%s, bdoId=%s]",
            personId,
            administrativeTenant,
            tenants,
            type,
            formOfAddress,
            academicTitle,
            academicTitlePostNominal,
            firstName,
            lastName,
            username,
            credentialsAvailable,
            approved,
            approvalState,
            externalId,
            guid,
            preferredUserId,
            personnelNumber,
            birthdate,
            email,
            phoneNumber,
            mobileNumber,
            locked,
            languages,
            companies,
            functions,
            numbers,
            contactCompanyId,
            contactCompanyMatchcode,
            contactCompanyNumber,
            portraitAvailable,
            portraitUuid,
            portraitThumbnailUuid,
            lastUpdate,
            score,
            taxNumber,
            bdoId
        );
    }
}
