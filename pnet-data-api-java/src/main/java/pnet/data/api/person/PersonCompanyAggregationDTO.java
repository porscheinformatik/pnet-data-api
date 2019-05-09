package pnet.data.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonCompanyAggregationDTO
{

    private final Integer companyId;
    private final long count;

    public PersonCompanyAggregationDTO(@JsonProperty("companyId") Integer companyId, @JsonProperty("count") long count)
    {
        super();

        this.companyId = companyId;
        this.count = count;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public long getCount()
    {
        return count;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s", companyId, count);
    }
}
