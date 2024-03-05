package pnet.data.api.util;

/**
 * Aggregates the number of items per state.
 *
 * @param <SELF> the type of the aggregate for chaining
 * @author cet
 */
public interface AggregateNumberPerState<SELF extends Aggregate<SELF>> extends Aggregate<SELF>
{

    default SELF aggregateNumberPerState()
    {
        return aggregate("number-per-state");
    }

}
