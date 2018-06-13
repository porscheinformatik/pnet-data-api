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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pnet.data.api.GeoPoint;

/**
 * Holds companydata.
 *
 * @author ham
 */
@ApiModel(description = "Holds all information about a company")
public class CompanyDataDTO implements Serializable
{

    private static final long serialVersionUID = -6550030919773234742L;

    @ApiModelProperty(notes = "The unique id of the company")
    private final Integer companyId;

    @ApiModelProperty(notes = "The tenant, in which this company is administrated")
    private String administrativeTenant;
    @ApiModelProperty(notes = "The name of the company")
    private String name;
    @ApiModelProperty(notes = "The name affix of the company")
    private String nameAffix;
    @ApiModelProperty(notes = "The marketing name of the company")
    private String marketingName;
    @ApiModelProperty(notes = "The tenants where the company is valid")
    private Collection<String> tenants;
    @ApiModelProperty(notes = "The brands where the company is valid")
    private Collection<CompanyBrandLinkDTO> brands;
    @ApiModelProperty(notes = "The contract types where the company is valid")
    private Collection<CompanyContractTypeLinkDTO> contractTypes;
    @ApiModelProperty(notes = "The contract states where the company is valid")
    private Collection<CompanyContractStateLinkDTO> contractStates;
    @ApiModelProperty(notes = "The vat ID of the company")
    private String vatIdNumber;
    @ApiModelProperty(notes = "The sap number of the of the company")
    private String sapNumber;
    @ApiModelProperty(notes = "The company number")
    private String companyNumber;
    @ApiModelProperty(notes = "The additional numbers of the company")
    private Collection<CompanyNumberLinkDTO> additionalNumbers;
    @ApiModelProperty(notes = "The street in which the company is located")
    private String street;
    @ApiModelProperty(notes = "The city in which the company is located")
    private String city;
    @ApiModelProperty(notes = "The postal code of the city in which the company is located")
    private String postalCode;
    @ApiModelProperty(notes = "The code of the country in which the company is located")
    private String countryCode;
    @ApiModelProperty(notes = "The name of the country the company is located in")
    private String country;
    @ApiModelProperty(notes = "The region of the country the company is in")
    private String region;
    @ApiModelProperty(notes = "The IBAN of the company")
    private String iban;
    @ApiModelProperty(notes = "The BIC of the company")
    private String bic;
    @ApiModelProperty(notes = "The company types the company has")
    private Collection<CompanyTypeLinkDTO> types;
    @ApiModelProperty(notes = "The phone number of the company")
    private String phoneNumber;
    @ApiModelProperty(notes = "The speed dial of the company")
    private String speedDial;
    @ApiModelProperty(notes = "The fax number of the company")
    private String faxNumber;
    @ApiModelProperty(notes = "The email address of the company")
    private String email;
    @ApiModelProperty(notes = "The homepage of the company")
    private String homepage;
    @ApiModelProperty(notes = "The postal address of the company")
    private String postal;
    @ApiModelProperty(notes = "The matchcode of the legal form")
    private String legalFormMatchcode;
    @ApiModelProperty(notes = "The DVR number of the company")
    private String dataProcessingRegisterNumber;
    @ApiModelProperty(notes = "The FB number of the company")
    private String commercialRegisterNumber;
    @ApiModelProperty(notes = "The certificate type the company has")
    private String certificateType;
    @ApiModelProperty(notes = "The number of the certificate the company has")
    private String certificateNumber;
    @ApiModelProperty(notes = "The venue jurisdiction the company is part of because of the geographical position")
    private String jurisdiction;
    @ApiModelProperty(notes = "The location of the company in form of a GeoPoint")
    private GeoPoint location;
    @ApiModelProperty(notes = "The external brands the company has")
    private Collection<CompanyExternalBrandDataDTO> externalBrands;
    @ApiModelProperty(notes = "The id of the company, that is the headquarter to this company")
    private Integer headquarterCompanyId;
    @ApiModelProperty(notes = "The time and date when the company was last changed")
    private LocalDateTime lastUpdate;

    public CompanyDataDTO(@JsonProperty("companyId") Integer companyId)
    {
        super();

        this.companyId = companyId;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public String getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public void setAdministrativeTenant(String administrativeTenant)
    {
        this.administrativeTenant = administrativeTenant;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNameAffix()
    {
        return nameAffix;
    }

    public void setNameAffix(String nameAffix)
    {
        this.nameAffix = nameAffix;
    }

    public String getMarketingName()
    {
        return marketingName;
    }

    public void setMarketingName(String marketingName)
    {
        this.marketingName = marketingName;
    }

    public Collection<String> getTenants()
    {
        return tenants;
    }

    public void setTenants(Collection<String> tenants)
    {
        this.tenants = tenants;
    }

    public Collection<CompanyBrandLinkDTO> getBrands()
    {
        return brands;
    }

    public void setBrands(Collection<CompanyBrandLinkDTO> brands)
    {
        this.brands = brands;
    }

    public Collection<CompanyContractTypeLinkDTO> getContractTypes()
    {
        return contractTypes;
    }

    public void setContractTypes(Collection<CompanyContractTypeLinkDTO> contractTypes)
    {
        this.contractTypes = contractTypes;
    }

    public Collection<CompanyContractStateLinkDTO> getContractStates()
    {
        return contractStates;
    }

    public void setContractStates(Collection<CompanyContractStateLinkDTO> contractStates)
    {
        this.contractStates = contractStates;
    }

    public String getVatIdNumber()
    {
        return vatIdNumber;
    }

    public void setVatIdNumber(String vatIdNumber)
    {
        this.vatIdNumber = vatIdNumber;
    }

    public String getSapNumber()
    {
        return sapNumber;
    }

    public void setSapNumber(String sapNumber)
    {
        this.sapNumber = sapNumber;
    }

    public String getCompanyNumber()
    {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber)
    {
        this.companyNumber = companyNumber;
    }

    public Collection<CompanyNumberLinkDTO> getAdditionalNumbers()
    {
        return additionalNumbers;
    }

    public void setAdditionalNumbers(Collection<CompanyNumberLinkDTO> additionalNumbers)
    {
        this.additionalNumbers = additionalNumbers;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getIban()
    {
        return iban;
    }

    public void setIban(String iban)
    {
        this.iban = iban;
    }

    public String getBic()
    {
        return bic;
    }

    public void setBic(String bic)
    {
        this.bic = bic;
    }

    public Collection<CompanyTypeLinkDTO> getTypes()
    {
        return types;
    }

    public void setTypes(Collection<CompanyTypeLinkDTO> types)
    {
        this.types = types;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getSpeedDial()
    {
        return speedDial;
    }

    public void setSpeedDial(String speedDial)
    {
        this.speedDial = speedDial;
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

    public String getHomepage()
    {
        return homepage;
    }

    public void setHomepage(String homepage)
    {
        this.homepage = homepage;
    }

    public String getPostal()
    {
        return postal;
    }

    public void setPostal(String postal)
    {
        this.postal = postal;
    }

    public String getLegalFormMatchcode()
    {
        return legalFormMatchcode;
    }

    public void setLegalFormMatchcode(String legalFormMatchcode)
    {
        this.legalFormMatchcode = legalFormMatchcode;
    }

    public String getDataProcessingRegisterNumber()
    {
        return dataProcessingRegisterNumber;
    }

    public void setDataProcessingRegisterNumber(String dataProcessingRegisterNumber)
    {
        this.dataProcessingRegisterNumber = dataProcessingRegisterNumber;
    }

    public String getCommercialRegisterNumber()
    {
        return commercialRegisterNumber;
    }

    public void setCommercialRegisterNumber(String commercialRegisterNumber)
    {
        this.commercialRegisterNumber = commercialRegisterNumber;
    }

    public String getCertificateType()
    {
        return certificateType;
    }

    public void setCertificateType(String certificateType)
    {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber()
    {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber)
    {
        this.certificateNumber = certificateNumber;
    }

    public String getJurisdiction()
    {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction)
    {
        this.jurisdiction = jurisdiction;
    }

    public GeoPoint getLocation()
    {
        return location;
    }

    public void setLocation(GeoPoint location)
    {
        this.location = location;
    }

    public Collection<CompanyExternalBrandDataDTO> getExternalBrands()
    {
        return externalBrands;
    }

    public void setExternalBrands(Collection<CompanyExternalBrandDataDTO> externalBrands)
    {
        this.externalBrands = externalBrands;
    }

    public Integer getHeadquarterCompanyId()
    {
        return headquarterCompanyId;
    }

    public void setHeadquarterCompanyId(Integer headquarterCompanyId)
    {
        this.headquarterCompanyId = headquarterCompanyId;
    }

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
            "CompanyDataDTO [companyId=%s, administrativeTenant=%s, name=%s, nameAffix=%s, marketingName=%s, "
                + "tenants=%s, brands=%s, contractTypes=%s, contractStates=%s, vatIdNumber=%s, sapNumber=%s, "
                + "companyNumber=%s, additionalNumbers=%s, street=%s, city=%s, postalCode=%s, countryCode=%s, country=%s, "
                + "region=%s, iban=%s, bic=%s, types=%s, phoneNumber=%s, speedDial=%s, faxNumber=%s, email=%s, "
                + "homepage=%s, postal=%s, legalFormMatchcode=%s, dataProcessingRegisterNumber=%s, commercialRegisterNumber=%s, "
                + "certificateType=%s, certificateNumber=%s, "
                + "jurisdiction=%s, location=%s, externalBrands=%s, headquarterCompanyId=%s, lastUpdate=%s]",
            companyId, administrativeTenant, name, nameAffix, marketingName, tenants, brands, contractTypes,
            contractStates, vatIdNumber, sapNumber, companyNumber, additionalNumbers, street, city, postalCode,
            countryCode, country, region, iban, bic, types, phoneNumber, speedDial, faxNumber, email, homepage, postal,
            legalFormMatchcode, dataProcessingRegisterNumber, commercialRegisterNumber, certificateType,
            certificateNumber, jurisdiction, location, externalBrands, headquarterCompanyId, lastUpdate);
    }

}
