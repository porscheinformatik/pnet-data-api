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

import pnet.data.api.GeoPoint;

/**
 * Holds companydata.
 *
 * @author ham
 */
public class CompanyDataDTO implements Serializable
{

    private static final long serialVersionUID = -6550030919773234742L;

    private final Integer companyId;

    private String administrativeTenant;
    private String name;
    private String nameAffix;
    private String marketingName;
    private Collection<String> tenants;
    private Collection<CompanyBrandLinkDTO> brands;
    private Collection<CompanyContractTypeLinkDTO> contractTypes;
    private Collection<CompanyContractStateLinkDTO> contractStates;
    private String vatIdNumber;
    private String sapNumber;
    private String companyNumber;
    private Collection<CompanyNumberLinkDTO> additionalNumbers;
    private String street;
    private String city;
    private String postalCode;
    private String countryCode;
    private String country;
    private String region;
    private String iban;
    private String bic;
    private Collection<CompanyTypeLinkDTO> types;
    private String phoneNumber;
    private String speedDial;
    private String faxNumber;
    private String email;
    private String homepage;
    private String postal;
    private String legalFormMatchcode;
    private String dataProcessingRegisterNumber;
    private String commercialRegisterNumber;
    private String certificateType;
    private String certificateNumber;
    private String jurisdiction;
    private GeoPoint location;
    private Collection<CompanyExternalBrandDataDTO> externalBrands;
    private Integer headquarterCompanyId;
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
