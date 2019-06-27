package pnet.data.api.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public abstract class AbstractCountAggregationDTO implements Serializable
{

    private static final long serialVersionUID = -4778141116767126304L;
    
    private final String matchcode;
    private final long count;

    public AbstractCountAggregationDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("count") long count)
    {
        super();

        this.matchcode = matchcode;
        this.count = count;
    }

    public String getMatchcode()
    {
        return matchcode;
    }

    public long getCount()
    {
        return count;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s", matchcode, count);
    }
}
