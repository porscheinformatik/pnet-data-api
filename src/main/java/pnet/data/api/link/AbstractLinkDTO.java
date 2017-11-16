package pnet.data.api.link;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.Matchcode;
import pnet.data.api.tenant.Tenant;

/**
 * A link to an object using a matchcode for a specified tenant.
 *
 * @author ham
 */
public abstract class AbstractLinkDTO<MatchcodeT extends Matchcode>
{

    private final Tenant tenant;
    private final MatchcodeT matchcode;

    public AbstractLinkDTO(@JsonProperty("tenant") Tenant tenant, @JsonProperty("matchcode") MatchcodeT matchcode)
    {
        super();

        this.tenant = tenant;
        this.matchcode = matchcode;
    }

    /**
     * @return The tenant.
     */
    public Tenant getTenant()
    {
        return tenant;
    }

    /**
     * @return The matchcode.
     */
    public MatchcodeT getMatchcode()
    {
        return matchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((matchcode == null) ? 0 : matchcode.hashCode());
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

        AbstractLinkDTO<?> other = (AbstractLinkDTO<?>) obj;

        if (matchcode == null)
        {
            if (other.matchcode != null)
            {
                return false;
            }
        }
        else if (!matchcode.equals(other.matchcode))
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
        return String.format("%2$s(%1$s)", tenant, matchcode);
    }

}
