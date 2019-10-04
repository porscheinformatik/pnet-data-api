package pnet.data.api.person;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonTenantAggregationDTO implements Serializable
{

    private static final long serialVersionUID = -4022467263764264661L;

    private final String tenant;
    private final long count;

    public PersonTenantAggregationDTO(@JsonProperty("tenant") String tenant, @JsonProperty("count") long count)
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
