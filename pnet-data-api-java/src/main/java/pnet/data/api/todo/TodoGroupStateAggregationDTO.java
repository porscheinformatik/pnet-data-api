package pnet.data.api.todo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The aggregation of one state
 *
 * @author cet
 */
public class TodoGroupStateAggregationDTO implements Serializable
{
    private static final long serialVersionUID = -6434116628397754095L;
    
    private final TodoState state;
    private final long count;

    public TodoGroupStateAggregationDTO(@JsonProperty("state") TodoState state, @JsonProperty("count") long count)
    {
        super();

        this.state = state;
        this.count = count;
    }

    public TodoState getState()
    {
        return state;
    }

    public long getCount()
    {
        return count;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s", state, count);
    }
}
