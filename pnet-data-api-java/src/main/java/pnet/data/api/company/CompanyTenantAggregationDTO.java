package pnet.data.api.company;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class CompanyTenantAggregationDTO implements Serializable
{

    private static final long serialVersionUID = -2289832270088868720L;

    private final String tenant;
    private final long count;

    public CompanyTenantAggregationDTO(@JsonProperty("tenant") String tenant, @JsonProperty("count") Long count)
    {
        super();

        this.tenant = tenant;
        this.count = count != null ? count : 0;
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
