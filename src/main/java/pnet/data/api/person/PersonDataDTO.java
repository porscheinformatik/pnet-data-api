package pnet.data.api.person;

import java.time.LocalDateTime;
import java.util.Collection;

import pnet.data.api.tenant.Tenant;
import pnet.data.api.util.WithLastUpdate;

/**
 * Holds one person.
 *
 * @author ham
 */
public class PersonDataDTO implements WithLastUpdate
{

    private Integer personId;
    private Tenant administrativeTenant;
    private FormOfAddress formOfAddress;
    private String academicTitle;
    private String firstName;
    private String lastName;
    private String userName;
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
    private LocalDateTime lastUpdate;

    public Integer getPersonId()
    {
        return personId;
    }

    public void setPersonId(Integer personId)
    {
        this.personId = personId;
    }

    public Tenant getAdministrativeTenant()
    {
        return administrativeTenant;
    }

    public void setAdministrativeTenant(Tenant administrativeTenant)
    {
        this.administrativeTenant = administrativeTenant;
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

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
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

    public void setCompanies(Collection<PersonCompanyLinkDTO> companies)
    {
        this.companies = companies;
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

}
