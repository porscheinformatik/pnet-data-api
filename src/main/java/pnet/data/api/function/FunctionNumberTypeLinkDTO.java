package pnet.data.api.function;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.numbertype.NumberTypeMatchcode;
import pnet.data.api.tenant.Tenant;

/**
 * Holds a link to a number type.
 *
 * @author ham
 */
public class FunctionNumberTypeLinkDTO
{

    private final Tenant tenant;
    private final NumberTypeMatchcode numberTypeMatchcode;

    public FunctionNumberTypeLinkDTO(@JsonProperty("tenant") Tenant tenant,
        @JsonProperty("numberTypeMatchcode") NumberTypeMatchcode numberTypeMatchcode)
    {
        super();

        this.tenant = tenant;
        this.numberTypeMatchcode = numberTypeMatchcode;
    }

    public Tenant getTenant()
    {
        return tenant;
    }

    public NumberTypeMatchcode getNumberTypeMatchcode()
    {
        return numberTypeMatchcode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((numberTypeMatchcode == null) ? 0 : numberTypeMatchcode.hashCode());
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

        FunctionNumberTypeLinkDTO other = (FunctionNumberTypeLinkDTO) obj;

        if (numberTypeMatchcode == null)
        {
            if (other.numberTypeMatchcode != null)
            {
                return false;
            }
        }
        else if (!numberTypeMatchcode.equals(other.numberTypeMatchcode))
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
        return String.format("%s(%s)", numberTypeMatchcode, tenant);
    }

}
