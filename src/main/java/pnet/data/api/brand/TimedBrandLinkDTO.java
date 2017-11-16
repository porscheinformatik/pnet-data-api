package pnet.data.api.brand;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.tenant.Tenant;

/**
 * A link to a brand for a specified tenant and valid for a specified period of time.
 *
 * @author ham
 */
public class TimedBrandLinkDTO extends BrandLinkDTO
{

    private final LocalDateTime validFrom;
    private final LocalDateTime validTo;

    public TimedBrandLinkDTO(@JsonProperty("tenant") Tenant tenant, @JsonProperty("matchcode") BrandMatchcode matchcode,
        @JsonProperty("validFrom") LocalDateTime validFrom, @JsonProperty("validTo") LocalDateTime validTo)
    {
        super(tenant, matchcode);

        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public LocalDateTime getValidFrom()
    {
        return validFrom;
    }

    public LocalDateTime getValidTo()
    {
        return validTo;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!super.equals(obj))
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        TimedBrandLinkDTO other = (TimedBrandLinkDTO) obj;

        if (validFrom == null)
        {
            if (other.validFrom != null)
            {
                return false;
            }
        }
        else if (!validFrom.equals(other.validFrom))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s [%3$s-%4$s]", super.toString(), validFrom, validTo);
    }

}
