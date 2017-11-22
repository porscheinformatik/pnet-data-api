package pnet.data.api.function;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.activity.ActivityMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds a link to an activity
 *
 * @author ham
 */
public class FunctionActivityLinkDTO
{

    private final Tenant tenant;
    private final ActivityMatchcode activityMatchcode;

    public FunctionActivityLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("activityMatchcode") ActivityMatchcode activityMatchcode)
    {
        super();

        this.tenant = tenant;
        this.activityMatchcode = activityMatchcode;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public ActivityMatchcode getActivityMatchcode()
    {
        return activityMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((activityMatchcode == null) ? 0 : activityMatchcode.hashCode());
        result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        FunctionActivityLinkDTO other = (FunctionActivityLinkDTO) obj;

        if (activityMatchcode == null)
        {
            if (other.activityMatchcode != null)
            {
                return false;
            }
        }
        else if (!activityMatchcode.equals(other.activityMatchcode))
        {
            return false;
        }

        if (tenant == null)
        {
            if (other.tenant != null)
            {
                return false;
            }
        }
        else if (!tenant.equals(other.tenant))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s(%s)", activityMatchcode, tenant);
    }

}
