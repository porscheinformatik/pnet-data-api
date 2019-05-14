package pnet.data.api.util;

/**
 * Aggregates the number of items per state.
 *
 * @author cet
 * @param <SELF> the type of the aggregate for chaining
 */
public interface AggregateNumberPerState<SELF extends Aggregate<SELF>> extends Aggregate<SELF>
{

    default SELF aggregateNumberPerState()
    {
        return aggregate("number-per-state");
    }

}
