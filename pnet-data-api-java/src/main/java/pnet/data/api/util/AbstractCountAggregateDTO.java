package pnet.data.api.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds the one aggregation
 *
 * @author HAM
 */
public abstract class AbstractCountAggregateDTO
{

    private final String matchcode;
    private final long count;

    public AbstractCountAggregateDTO(@JsonProperty("matchcode") String matchcode, @JsonProperty("count") long count)
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
