package pnet.data.api.person;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public class PersonCompanyAggregationDTO implements Serializable
{

    private static final long serialVersionUID = -2588191893345417781L;
    
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
