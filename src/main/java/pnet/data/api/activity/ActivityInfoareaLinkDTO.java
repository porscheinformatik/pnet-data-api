package pnet.data.api.activity;

import pnet.data.api.infoarea.InfoareaMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds a link to an infoarea
 *
 * @author ham
 */
public class ActivityInfoareaLinkDTO
{

    private final Tenant tenant;
    private final InfoareaMatchcode infoareaMatchcode;

    public ActivityInfoareaLinkDTO(Tenant tenant, InfoareaMatchcode infoareaMatchcode)
    {
        super();
        this.tenant = tenant;
        this.infoareaMatchcode = infoareaMatchcode;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public InfoareaMatchcode getInfoareaMatchcode()
    {
        return infoareaMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((infoareaMatchcode == null) ? 0 : infoareaMatchcode.hashCode());
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
        ActivityInfoareaLinkDTO other = (ActivityInfoareaLinkDTO) obj;
        if (infoareaMatchcode == null)
        {
            if (other.infoareaMatchcode != null)
            {
                return false;
            }
        }
        else if (!infoareaMatchcode.equals(other.infoareaMatchcode))
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
        return String.format("%s(%s)", infoareaMatchcode, tenant);
    }

}
