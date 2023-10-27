package pnet.data.api.settings;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.util.WithTenant;

public abstract class AbstractSettingsLinkDTO implements WithTenant, Serializable
{
    private static final long serialVersionUID = 9065998075301387296L;

    protected final String tenant;

    public AbstractSettingsLinkDTO(@JsonProperty("tenant") String tenant)
    {
        super();
        this.tenant = tenant;
    }

    @Override
    public String getTenant()
    {
        return tenant;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(tenant);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof AbstractSettingsLinkDTO other))
        {
            return false;
        }
        return Objects.equals(tenant, other.tenant);
    }

    @Override
    public String toString()
    {
        return String.format("AbstractSettingsLinkDTO [tenant=%s]", tenant);
    }
}
