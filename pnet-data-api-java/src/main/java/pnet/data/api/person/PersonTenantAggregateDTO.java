package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonTenantAggregateDTO
{

    private final String tenant;
    private final long count;

    public PersonTenantAggregateDTO(@JsonProperty("tenant") String tenant, @JsonProperty("count") long count)
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
