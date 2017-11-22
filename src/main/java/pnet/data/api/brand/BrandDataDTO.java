package pnet.data.api.brand;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.tenant.Tenant;
import pnet.data.api.tenant.WithTenants;
import pnet.data.api.util.WithLabels;
import pnet.data.api.util.WithLastUpdate;
import pnet.data.api.util.WithMatchcode;

/**
 * Holds a brand as specified in the Partner.Net.
 *
 * @author ham
 */
public class BrandDataDTO implements WithMatchcode<BrandMatchcode>, WithTenants, WithLabels, WithLastUpdate
{

    private final BrandMatchcode matchcode;

    private Collection<Tenant> tenants;
    private Map<Locale, String> labels;
    private int ordinal;
    private String path;
    private LocalDateTime lastUpdate;

    public BrandDataDTO(@JsonProperty("matchcode") BrandMatchcode matchcode)
    {
        super();

        this.matchcode = matchcode;
    }

    @Override
    public BrandMatchcode getMatchcode()
    {
        return matchcode;
    }

    @Override
    public Collection<Tenant> getTenants()
    {
        return tenants;
    }

    public void setTenants(Collection<Tenant> tenants)
    {
        this.tenants = tenants;
    }

    @Override
    public Map<Locale, String> getLabels()
    {
        return labels;
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
