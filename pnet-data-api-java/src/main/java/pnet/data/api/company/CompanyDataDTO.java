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
package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import pnet.data.api.GeoPoint;
import pnet.data.api.companygroup.CompanyGroupMemberLinkDTO;
import pnet.data.api.util.PnetDataApiUtils;
import pnet.data.api.util.WithCompanyId;
import pnet.data.api.util.WithMatchcode;
import pnet.data.api.util.WithTenants;

/**
 * Holds companydata.
 *
 * @author ham
 */
@Schema(description = "Holds all information about one company.")
public class CompanyDataDTO implements WithCompanyId, WithMatchcode, WithTenants, Serializable {

    @Serial
    private static final long serialVersionUID = 4995140302871305715L;

    @Schema(description = "The unique id of the company (also known as GP-ID).")
    private final Integer companyId;

    @Schema(description = "The matchcode of the external brand (fits the matchcodes of the external brand interface.")
    private String matchcode;

    @Schema(description = "The tenant (Portal-ID), in which this company gets administrated.")
    private String administrativeTenant;

    @Schema(description = "The label of the company (either the marketing name or a combination of name and affix).")
    private String label;

    @Schema(description = "The name of the company.")
    private String name;

    @Schema(description = "The name affix of the company.")
    private String nameAffix;

    @Schema(description = "The additional name affix of the company.")
    private String additionalNameAffix;

    @Schema(description = "The marketing name of the company.")
    private String marketingName;

    @Schema(description = "An optional additional marketing name for certain companies.")
    private String additionalMarketingName;

    @Schema(description = "Groups this company is part of.")
    private Collection<CompanyGroupMemberLinkDTO> groupMembers;

    @Schema(description = "Valid tenants of the company (also known as Portal-ID).")
    private Collection<String> tenants;

    @Schema(
        description = "All brands assigned to the company. The matchcode of each item fits to the matchcodes of the brands " +
            "interface."
    )
    private Collection<CompanyBrandLinkDTO> brands;

    @Schema(
        description = "All contract types assigned to the company. The matchcode of each item fits to the matchcodes of the " +
            "contract types interface."
    )
    private Collection<CompanyContractTypeLinkDTO> contractTypes;

    @Schema(
        description = "All contract states assigned to the company. The matchcode of each item fits to the matchcodes of the " +
            "contract states interface."
    )
    private Collection<CompanyContractStateLinkDTO> contractStates;

    @Schema(description = "All contract distribution structures assigned to the company.")
    private Collection<CompanyContractDistributionStructureLinkDTO> contractDistributionStructure;

    @Schema(description = "The vat ID of the company.")
    private String vatIdNumber;

    /**
     * The SAP number.
     *
     * @deprecated use {@link #companyNumber} instead
     */
    @Schema(description = "The SAP number of the of the company. Deprecated: use company number instead.")
    @Deprecated
    private String sapNumber;

    @Schema(description = "The company number. In most cases, this is the same as the SAP number.")
    private String companyNumber;

    @Schema(description = "The UUID of the company as specified in the BPCM system.")
    private String bpcmLocationUuid;

    @Schema(description = "If true, the company is partly configured and managed by the BPCM system.")
    private boolean bpcmManaged;

    @Schema(
        description = "All additional numbers of the company. The matchcode of each item fits to the matchcodes of the " +
            "company number types interface."
    )
    private Collection<CompanyNumberLinkDTO> additionalNumbers;

    @Schema(description = "The name of the street as defined in the address of the company.")
    private String street;

    @Schema(description = "The name of the city as defined in the address of the company.")
    private String city;

    @Schema(description = "The postal code of the city as defined in the address of the company.")
    private String postalCode;

    @Schema(description = "The code of the country as defined in the address of the company.")
    private String countryCode;

    @Schema(description = "The name of the country as defined in the address of the company.")
    private String country;

    @Schema(description = "The region as defined in the address of the company.")
    private String region;

    @Schema(description = "The IBAN of the company.")
    private String iban;

    @Schema(description = "The BIC of the company.")
    private String bic;

    @Schema(
        description = "All company types assigned to the company. The matchcode of each item fits to the " +
            "matchcodes of the company types interface."
    )
    private Collection<CompanyTypeLinkDTO> types;

    @Schema(description = "The phone number of the company.")
    private String phoneNumber;

    @Schema(description = "The mobile phone number of the company.")
    private String mobileNumber;

    /**
     * @deprecated will be removed in future
     */
    @Schema(description = "The speed dial of the company. Will be removed in future, as it is not maintained anymore.")
    @Deprecated
    private String speedDial;

    @Schema(description = "The fax number of the company.")
    private String faxNumber;

    @Schema(description = "The email address of the company.")
    private String email;

    @Schema(description = "The lead email address of the company.")
    private String leadEmail;

    @Schema(description = "The homepage of the company.")
    private String homepage;

    @Schema(
        description = "The postal address of the company. In contrast to the other address fields, this may " +
            "contain a simplifcation of the address, like a post office box."
    )
    private String postal;

    @Schema(description = "A link to the Facebook page of the company.")
    private String facebookLink;

    @Schema(description = "A link to the YouTube page of the company.")
    private String youTubeLink;

    @Schema(description = "A link to the Instagram page of the company.")
    private String instagramLink;

    @Schema(description = "A link to the Viber page of the company.")
    private String viberLink;

    @Schema(description = "A link to the Telegam page of the company.")
    private String telegramLink;

    @Schema(description = "A link to the TikTok page of the company.")
    private String tikTokLink;

    @Schema(description = "A link to the X(Twitter) page of the company.")
    private String xTwitterLink;

    @Schema(description = "The matchcode of the legal form.")
    private String legalFormMatchcode;

    @Schema(description = "The number of the company as defined in the data processing register (also known as DVR).")
    private String dataProcessingRegisterNumber;

    @Schema(description = "The number of the company in the commerical register (also knwon as FB).")
    private String commercialRegisterNumber;

    @Schema(description = "The certificate type of the company.")
    private String certificateType;

    @Schema(description = "The number of the certificate of the company.")
    private String certificateNumber;

    @Schema(description = "The venue jurisdiction the company is part of because of its geographical position.")
    private String jurisdiction;

    @Schema(
        description = "The provision in a company's constitution stating the purpose and range of activities for which the " +
            "company is carried on (part of the impressum)."
    )
    private String objectsClause;

    @Schema(description = "The general partner of the company (part of the impressum).")
    private String generalPartner;

    @Schema(description = "The registered office of the company (part of the impressum).")
    private String registeredOffice;

    @Schema(description = "The chamber affiliation of the company (part of the impressum).")
    private String chamberAffiliation;

    @Schema(description = "The commercial regulations of the company (part of the impressum).")
    private String commercialRegulations;

    @Schema(description = "The regulatory authorityof the company (part of the impressum).")
    private String regulatoryAuthority;

    @Schema(description = "The arbitration board of the company (part of the impressum).")
    private String arbitrationBoard;

    @Schema(description = "Some additional imprint information (part of the impressum).")
    private String additionalImprintInfo;

    @Schema(description = "The business information number (GISA) as used in Austria (part of the impressum).")
    private String businessInformationNumber;

    @Schema(description = "The logitude and latitude of the companies location.")
    private GeoPoint location;

    @Schema(description = "All external brands assigned to the company.")
    private Collection<CompanyExternalBrandDataDTO> externalBrands;

    @Schema(description = "Add advisors assigned to the company.")
    private Collection<CompanyAdvisorAssignmentLinkDTO> advisorAssignments;

    @Schema(
        description = "The time and date of the last occasion, when the data of the this company has been " +
            "modified."
    )
    private LocalDateTime lastUpdate;

    @Schema(description = "The date when the companies employees need to be recertified")
    private LocalDateTime recertValidTo;

    @Schema(description = "The date when the companies re-certifiers where last notified about recertification")
    private LocalDateTime recertNotified;

    public CompanyDataDTO(@JsonProperty("companyId") Integer companyId) {
        super();
        this.companyId = companyId;
    }

    @Override
    public Integer getCompanyId() {
        return companyId;
    }

    @Override
    public String getMatchcode() {
        return getCompanyMatchcode();
    }

    @Override
    public String getCompanyMatchcode() {
        return matchcode;
    }

    public void setMatchcode(String matchcode) {
        this.matchcode = matchcode;
    }

    public String getAdministrativeTenant() {
        return administrativeTenant;
    }

    public void setAdministrativeTenant(String administrativeTenant) {
        this.administrativeTenant = administrativeTenant;
    }

    public String getLabel() {
        return label;
    }

    public String getLabelWithNumber() {
        return PnetDataApiUtils.toCompanyLabelWithNumber(companyNumber, label);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAffix() {
        return nameAffix;
    }

    public void setNameAffix(String nameAffix) {
        this.nameAffix = nameAffix;
    }

    public String getAdditionalNameAffix() {
        return additionalNameAffix;
    }

    public void setAdditionalNameAffix(String additionalNameAffix) {
        this.additionalNameAffix = additionalNameAffix;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getAdditionalMarketingName() {
        return additionalMarketingName;
    }

    public void setAdditionalMarketingName(String additionalMarketingName) {
        this.additionalMarketingName = additionalMarketingName;
    }

    public Collection<CompanyGroupMemberLinkDTO> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Collection<CompanyGroupMemberLinkDTO> groupMembers) {
        this.groupMembers = groupMembers;
    }

    @Override
    public Collection<String> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<String> tenants) {
        this.tenants = tenants;
    }

    public Collection<CompanyBrandLinkDTO> getBrands() {
        return brands;
    }

    public Optional<CompanyBrandLinkDTO> findBrand(Predicate<? super CompanyBrandLinkDTO> predicate) {
        return brands == null ? Optional.empty() : brands.stream().filter(predicate).findFirst();
    }

    public void setBrands(Collection<CompanyBrandLinkDTO> brands) {
        this.brands = brands;
    }

    public Collection<CompanyContractTypeLinkDTO> getContractTypes() {
        return contractTypes;
    }

    public Optional<CompanyContractTypeLinkDTO> findContractType(
        Predicate<? super CompanyContractTypeLinkDTO> predicate
    ) {
        return contractTypes == null ? Optional.empty() : contractTypes.stream().filter(predicate).findFirst();
    }

    public void setContractTypes(Collection<CompanyContractTypeLinkDTO> contractTypes) {
        this.contractTypes = contractTypes;
    }

    public Collection<CompanyContractStateLinkDTO> getContractStates() {
        return contractStates;
    }

    public Optional<CompanyContractStateLinkDTO> findContractState(
        Predicate<? super CompanyContractStateLinkDTO> predicate
    ) {
        return contractStates == null ? Optional.empty() : contractStates.stream().filter(predicate).findFirst();
    }

    public void setContractStates(Collection<CompanyContractStateLinkDTO> contractStates) {
        this.contractStates = contractStates;
    }

    public Collection<CompanyContractDistributionStructureLinkDTO> getContractDistributionStructure() {
        return contractDistributionStructure;
    }

    public Optional<CompanyContractDistributionStructureLinkDTO> findContractDistributionStructure(
        Predicate<? super CompanyContractDistributionStructureLinkDTO> predicate
    ) {
        return contractDistributionStructure == null
            ? Optional.empty()
            : contractDistributionStructure.stream().filter(predicate).findFirst();
    }

    public void setContractDistributionStructure(
        Collection<CompanyContractDistributionStructureLinkDTO> contractDistributionStructure
    ) {
        this.contractDistributionStructure = contractDistributionStructure;
    }

    public String getVatIdNumber() {
        return vatIdNumber;
    }

    public void setVatIdNumber(String vatIdNumber) {
        this.vatIdNumber = vatIdNumber;
    }

    /**
     * @return the SAP number
     * @deprecated use {@link #getCompanyNumber()} instead
     */
    @Deprecated
    public String getSapNumber() {
        return sapNumber;
    }

    /**
     * Sets the SAP number.
     *
     * @param sapNumber the SAP number
     * @deprecated use {@link #setCompanyNumber(String)} instead
     */
    @Deprecated
    public void setSapNumber(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    @Override
    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getBpcmLocationUuid() {
        return bpcmLocationUuid;
    }

    public void setBpcmLocationUuid(String bpcmLocationUuid) {
        this.bpcmLocationUuid = bpcmLocationUuid;
    }

    public boolean isBpcmManaged() {
        return bpcmManaged;
    }

    public void setBpcmManaged(boolean bpcmManaged) {
        this.bpcmManaged = bpcmManaged;
    }

    public Collection<CompanyNumberLinkDTO> getAdditionalNumbers() {
        return additionalNumbers;
    }

    public Optional<CompanyNumberLinkDTO> findAdditionalNumber(Predicate<? super CompanyNumberLinkDTO> predicate) {
        return additionalNumbers == null ? Optional.empty() : additionalNumbers.stream().filter(predicate).findFirst();
    }

    public void setAdditionalNumbers(Collection<CompanyNumberLinkDTO> additionalNumbers) {
        this.additionalNumbers = additionalNumbers;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public Collection<CompanyTypeLinkDTO> getTypes() {
        return types;
    }

    public Optional<CompanyTypeLinkDTO> findType(Predicate<? super CompanyTypeLinkDTO> predicate) {
        return types == null ? Optional.empty() : types.stream().filter(predicate).findFirst();
    }

    public void setTypes(Collection<CompanyTypeLinkDTO> types) {
        this.types = types;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the speed dial (unused)
     * @deprecated will be removed in future
     */
    @Deprecated
    public String getSpeedDial() {
        return speedDial;
    }

    /**
     * Sets the speed dial (unused)
     *
     * @param speedDial the speed dial
     * @deprecated will be removed in future
     */
    @Deprecated
    public void setSpeedDial(String speedDial) {
        this.speedDial = speedDial;
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

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getViberLink() {
        return viberLink;
    }

    public void setViberLink(String viberLink) {
        this.viberLink = viberLink;
    }

    public String getTelegramLink() {
        return telegramLink;
    }

    public void setTelegramLink(String telegramLink) {
        this.telegramLink = telegramLink;
    }

    public String getTikTokLink() {
        return tikTokLink;
    }

    public void setTikTokLink(String tikTokLink) {
        this.tikTokLink = tikTokLink;
    }

    @JsonProperty("xTwitterLink")
    public String getXTwitterLink() {
        return xTwitterLink;
    }

    @JsonProperty("xTwitterLink")
    public void setXTwitterLink(String xTwitterLink) {
        this.xTwitterLink = xTwitterLink;
    }

    public String getLegalFormMatchcode() {
        return legalFormMatchcode;
    }

    public void setLegalFormMatchcode(String legalFormMatchcode) {
        this.legalFormMatchcode = legalFormMatchcode;
    }

    public String getDataProcessingRegisterNumber() {
        return dataProcessingRegisterNumber;
    }

    public void setDataProcessingRegisterNumber(String dataProcessingRegisterNumber) {
        this.dataProcessingRegisterNumber = dataProcessingRegisterNumber;
    }

    public String getCommercialRegisterNumber() {
        return commercialRegisterNumber;
    }

    public void setCommercialRegisterNumber(String commercialRegisterNumber) {
        this.commercialRegisterNumber = commercialRegisterNumber;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getObjectsClause() {
        return objectsClause;
    }

    public void setObjectsClause(String objectsClause) {
        this.objectsClause = objectsClause;
    }

    public String getGeneralPartner() {
        return generalPartner;
    }

    public void setGeneralPartner(String generalPartner) {
        this.generalPartner = generalPartner;
    }

    public String getRegisteredOffice() {
        return registeredOffice;
    }

    public void setRegisteredOffice(String registeredOffice) {
        this.registeredOffice = registeredOffice;
    }

    public String getChamberAffiliation() {
        return chamberAffiliation;
    }

    public void setChamberAffiliation(String chamberAffiliation) {
        this.chamberAffiliation = chamberAffiliation;
    }

    public String getCommercialRegulations() {
        return commercialRegulations;
    }

    public void setCommercialRegulations(String commercialRegulations) {
        this.commercialRegulations = commercialRegulations;
    }

    public String getRegulatoryAuthority() {
        return regulatoryAuthority;
    }

    public void setRegulatoryAuthority(String regulatoryAuthority) {
        this.regulatoryAuthority = regulatoryAuthority;
    }

    public String getArbitrationBoard() {
        return arbitrationBoard;
    }

    public void setArbitrationBoard(String arbitrationBoard) {
        this.arbitrationBoard = arbitrationBoard;
    }

    public String getAdditionalImprintInfo() {
        return additionalImprintInfo;
    }

    public void setAdditionalImprintInfo(String additionalImprintInfo) {
        this.additionalImprintInfo = additionalImprintInfo;
    }

    public String getBusinessInformationNumber() {
        return businessInformationNumber;
    }

    public void setBusinessInformationNumber(String businessInformationNumber) {
        this.businessInformationNumber = businessInformationNumber;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public Collection<CompanyExternalBrandDataDTO> getExternalBrands() {
        return externalBrands;
    }

    public Optional<CompanyExternalBrandDataDTO> findExternalBrand(
        Predicate<? super CompanyExternalBrandDataDTO> predicate
    ) {
        return externalBrands == null ? Optional.empty() : externalBrands.stream().filter(predicate).findFirst();
    }

    public void setExternalBrands(Collection<CompanyExternalBrandDataDTO> externalBrands) {
        this.externalBrands = externalBrands;
    }

    public Collection<CompanyAdvisorAssignmentLinkDTO> getAdvisorAssignments() {
        return advisorAssignments;
    }

    public Optional<CompanyAdvisorAssignmentLinkDTO> findAdvisorAssignment(
        Predicate<? super CompanyAdvisorAssignmentLinkDTO> predicate
    ) {
        return advisorAssignments == null
            ? Optional.empty()
            : advisorAssignments.stream().filter(predicate).findFirst();
    }

    public void setAdvisorAssignments(Collection<CompanyAdvisorAssignmentLinkDTO> advisorAssignments) {
        this.advisorAssignments = advisorAssignments;
    }

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

    public LocalDateTime getRecertNotified() {
        return recertNotified;
    }

    public void setRecertNotified(LocalDateTime recertNotified) {
        this.recertNotified = recertNotified;
    }

    @Override
    public String toString() {
        return String.format(
            "CompanyDataDTO [companyId=%s, matchcode=%s, administrativeTenant=%s, label=%s, name=%s, nameAffix=%s, " +
                "additionalNameAffix=%s, marketingName=%s, additionalMarketingName=%s, groupMembers=%s, tenants=%s, brands=%s, contractTypes=%s, " +
                "contractStates=%s, contractDistributionStructure=%s, vatIdNumber=%s, sapNumber=%s, " +
                "companyNumber=%s, bpcmLocationUuid=%s, bpcmManaged=%s, additionalNumbers=%s, street=%s, city=%s, " +
                "postalCode=%s, countryCode=%s, country=%s, region=%s, iban=%s, bic=%s, types=%s, phoneNumber=%s, " +
                "mobileNumber=%s, speedDial=%s, faxNumber=%s, email=%s, leadEmail=%s, homepage=%s, postal=%s, " +
                "facebookLink=%s, youTubeLink=%s, instagramLink=%s, viberLink=%s, telegramLink=%s, tikTokLink=%s, " +
                "xTwitterLink=%s, legalFormMatchcode=%s, dataProcessingRegisterNumber=%s, commercialRegisterNumber=%s, " +
                "certificateType=%s, certificateNumber=%s, jurisdiction=%s, objectsClause=%s, generalPartner=%s, " +
                "registeredOffice=%s, chamberAffiliation=%s, commercialRegulations=%s, regulatoryAuthority=%s, " +
                "arbitrationBoard=%s, additionalImprintInfo=%s, businessInformationNumber=%s, location=%s, " +
                "externalBrands=%s, advisorAssignments=%s, lastUpdate=%s, recertValidTo=%s, recertNotified=%s]",
            companyId,
            matchcode,
            administrativeTenant,
            label,
            name,
            nameAffix,
            additionalNameAffix,
            marketingName,
            additionalMarketingName,
            groupMembers,
            tenants,
            brands,
            contractTypes,
            contractStates,
            contractDistributionStructure,
            vatIdNumber,
            sapNumber,
            companyNumber,
            bpcmLocationUuid,
            bpcmManaged,
            additionalNumbers,
            street,
            city,
            postalCode,
            countryCode,
            country,
            region,
            iban,
            bic,
            types,
            phoneNumber,
            mobileNumber,
            speedDial,
            faxNumber,
            email,
            leadEmail,
            homepage,
            postal,
            facebookLink,
            youTubeLink,
            instagramLink,
            viberLink,
            telegramLink,
            tikTokLink,
            xTwitterLink,
            legalFormMatchcode,
            dataProcessingRegisterNumber,
            commercialRegisterNumber,
            certificateType,
            certificateNumber,
            jurisdiction,
            objectsClause,
            generalPartner,
            registeredOffice,
            chamberAffiliation,
            commercialRegulations,
            regulatoryAuthority,
            arbitrationBoard,
            additionalImprintInfo,
            businessInformationNumber,
            location,
            externalBrands,
            advisorAssignments,
            lastUpdate,
            recertValidTo,
            recertNotified
        );
    }
}

