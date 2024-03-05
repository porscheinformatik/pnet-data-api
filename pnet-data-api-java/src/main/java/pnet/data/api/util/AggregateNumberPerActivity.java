package pnet.data.api.util;

/**
 * Aggregates the number of items per activity.
 *
 * @param <SELF> the type of the aggregate for chaining
 * @author ham
 */
public interface AggregateNumberPerActivity<SELF extends Aggregate<SELF>> extends Aggregate<SELF>
{

    default SELF aggregateNumberPerActivity()
    {
        return aggregate("number-per-activity");
    }

}
