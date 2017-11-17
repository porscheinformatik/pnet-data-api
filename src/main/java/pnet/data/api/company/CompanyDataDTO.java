package pnet.data.api.company;

import java.time.LocalDateTime;
import java.util.Collection;

import pnet.data.api.companytype.CompanyTypeMatchcode;
import pnet.data.api.externalbrand.ExternalBrandMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds companydata.
 * 
 * @author ham
 */
public class CompanyDataDTO
{

    private Integer companyId;
    private Tenant administrativeTenant;
    private String name;
    private String nameAffix;
    private String marketingName;
    private String uidNumber;
    private String sapNumber;
    private String companyNumber;
    private Collection<CompanyNumberDataDTO> additionalNumbers;
    private String street;
    private String city;
    private String zip;
    private String countryCode;
    private String country;
    private String region;
    private String iban;
    private String bic;
    private Collection<CompanyTypeMatchcode> types;
    private String phoneNumber;
    private String mobileNumber;
    private String speedDial;
    private String faxNumber;
    private String email;
    private String homepage;
    private String postal;
    private Collection<String> legalForm;
    private String dvrNumber;
    private String fbNumber;
    private String certificateType;
    private String certificateNumber;
    private String juristiction;
    private double longitude;
    private double latitude;
    private Collection<CompanyAdvisorDataDTO> advisors;
    private Collection<ExternalBrandMatchcode> extenalBrands;
    private LocalDateTime lastUpdate;

    public Integer getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }

    public Tenant getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public void setAdministrativeTenant(Tenant administrativeTenant)
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

    public String getUidNumber()
    {
        return uidNumber;
    }

    public void setUidNumber(String uidNumber)
    {
        this.uidNumber = uidNumber;
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

    public Collection<CompanyNumberDataDTO> getAdditionalNumbers()
    {
        return additionalNumbers;
    }

    public void setAdditionalNumbers(Collection<CompanyNumberDataDTO> additionalNumbers)
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

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
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

    public Collection<CompanyTypeMatchcode> getTypes()
    {
        return types;
    }

    public void setTypes(Collection<CompanyTypeMatchcode> types)
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

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
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

    public Collection<String> getLegalForm()
    {
        return legalForm;
    }

    public void setLegalForm(Collection<String> legalForm)
    {
        this.legalForm = legalForm;
    }

    public String getDvrNumber()
    {
        return dvrNumber;
    }

    public void setDvrNumber(String dvrNumber)
    {
        this.dvrNumber = dvrNumber;
    }

    public String getFbNumber()
    {
        return fbNumber;
    }

    public void setFbNumber(String fbNumber)
    {
        this.fbNumber = fbNumber;
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

    public String getJuristiction()
    {
        return juristiction;
    }

    public void setJuristiction(String juristiction)
    {
        this.juristiction = juristiction;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public Collection<CompanyAdvisorDataDTO> getAdvisors()
    {
        return advisors;
    }

    public void setAdvisors(Collection<CompanyAdvisorDataDTO> advisors)
    {
        this.advisors = advisors;
    }

    public Collection<ExternalBrandMatchcode> getExtenalBrands()
    {
        return extenalBrands;
    }

    public void setExtenalBrands(Collection<ExternalBrandMatchcode> extenalBrands)
    {
        this.extenalBrands = extenalBrands;
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
                + "uidNumber=%s, sapNumber=%s, companyNumber=%s, additionalNumbers=%s, street=%s, city=%s, zip=%s, "
                + "countryCode=%s, country=%s, region=%s, iban=%s, bic=%s, types=%s, phoneNumber=%s, mobileNumber=%s, "
                + "speedDial=%s, faxNumber=%s, email=%s, homepage=%s, postal=%s, legalForm=%s, dvrNumber=%s, fbNumber=%s, "
                + "certificateType=%s, certificateNumber=%s, juristiction=%s, longitude=%s, latitude=%s, advisors=%s, "
                + "extenalBrands=%s, lastUpdate=%s]",
            companyId, administrativeTenant, name, nameAffix, marketingName, uidNumber, sapNumber, companyNumber,
            additionalNumbers, street, city, zip, countryCode, country, region, iban, bic, types, phoneNumber,
            mobileNumber, speedDial, faxNumber, email, homepage, postal, legalForm, dvrNumber, fbNumber,
            certificateType, certificateNumber, juristiction, longitude, latitude, advisors, extenalBrands, lastUpdate);
    }

}
