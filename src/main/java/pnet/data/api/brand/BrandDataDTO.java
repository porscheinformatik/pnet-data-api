package pnet.data.api.brand;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import pnet.data.api.tenant.MultiTenancy;
import pnet.data.api.tenant.Tenant;
import pnet.data.api.util.Traceable;
import pnet.data.api.util.Utils;

/**
 * Holds a brand as specified in the Partner.Net.
 *
 * @author ham
 */
public class BrandDataDTO implements MultiTenancy, Traceable
{

    private BrandMatchcode matchcode;
    private Collection<Tenant> tenants;
    private Map<Locale, String> labels;
    private int ordinal;
    private String path;
    private LocalDateTime lastUpdate;

    public BrandDataDTO()
    {
        super();
    }

    /**
     * @return The unique, alpha-numeric key of the brand: V, A, L, ... . This key is the same in all environments.
     */
    public BrandMatchcode getMatchcode()
    {
        return matchcode;
    }

    public void setMatchcode(BrandMatchcode matchcode)
    {
        this.matchcode = matchcode;
    }

    /**
     * @return A list of all tenants that support this brand.
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
     * @return A map of strings by locale, holding the label of the brand in multiple languages.
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
     * @return The ordinal of the brand for sorting.
     */
    public int getOrdinal()
    {
        return ordinal;
    }

    public void setOrdinal(int ordinal)
    {
        this.ordinal = ordinal;
    }

    /**
     * @return The simplified name when used as path, e.g. "vw", "audi", "vwlnf".
     */
    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
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
        return String.format("BrandDataDTO [matchcode=%s, tenants=%s, labels=%s, ordinal=%s, path=%s]", matchcode,
            tenants, labels, ordinal, path);
    }

}
