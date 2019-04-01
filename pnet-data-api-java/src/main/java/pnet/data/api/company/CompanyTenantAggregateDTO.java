package pnet.data.api.company;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class CompanyTenantAggregateDTO
{

    private final String tenant;
    private final long count;

    public CompanyTenantAggregateDTO(@JsonProperty("tenant") String tenant, @JsonProperty("count") long count)
    {
        super();

        this.tenant = tenant;
        this.count = count;
    }

    public String getTenant()
    {
        return tenant;
    }

    public long getCount()
    {
        return count;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s", tenant, count);
    }
}
