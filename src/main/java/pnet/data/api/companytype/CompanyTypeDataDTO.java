package pnet.data.api.companytype;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.tenant.MultiTenancy;
import pnet.data.api.tenant.Tenant;
import pnet.data.api.util.Utils;

/**
 * Holds a company type. A company is linked to one or more company types. Functions and activities need company types
 * as prerequisite.
 *
 * @author ham
 */
public class CompanyTypeDataDTO implements MultiTenancy
{

    private CompanyTypeMatchcode matchcode;
    private Collection<Tenant> tenants;
    private Map<Locale, String> labels;
    private int level;
    private boolean contractSpecific;
    private LocalDateTime lastUpdate;

    public CompanyTypeDataDTO()
    {
        super();
    }

    /**
     * @return The unique, alpha-numeric key of the company type. The key is the same in all environments.
     */
    public CompanyTypeMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(CompanyTypeMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    /**
     * @return A list of all tenants that support this company type.
     */
    @Override
    public Collection<Tenant> getTenants()
    {
        return tenants;
    }

    public void setTenants(Collection<Tenant> tenants)
    {
        this.tenants = tenants;
    }

    /**
     * @return A map of strings by locale, holding the label of the company type in multiple languages.
     */
    public Map<Locale, String> getLabels()
    {
        return labels;
    }

    /**
     * @param language the language, may be null
     * @return The label in the specified language, null if not found.
     */
    public String getLabel(Locale language)
    {
        return Utils.getText(language, labels);
    }

    public void setLabels(Map<Locale, String> labels)
    {
        this.labels = labels;
    }

    /**
     * @return The level of the company type.
     */
    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * @return True, if contract types have to be checked as prerequisite along with this company type. False, if it's
     *         enough, that company has this company type.
     */
    public boolean isContractSpecific()
    {
        return contractSpecific;
    }

    public void setContractSpecific(boolean contractSpecific)
    {
        this.contractSpecific = contractSpecific;
    }

    /**
     * @return The date/time of the last update to this item.
     */
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
        return String.format("CompanyTypeDataDTO [matchcode=%s, tenants=%s, labels=%s, level=%s, contractSpecific=%s]",
            matchcode, tenants, labels, level, contractSpecific);
    }

}
