package pnet.data.api.util;

/**
 * Aggregates the number of items per type.
 *
 * @param <SELF> the type of the aggregate for chaining
 * @author ham
 */
public interface AggregateNumberPerType<SELF extends Aggregate<SELF>> extends Aggregate<SELF>
{

    default SELF aggregateNumberPerType()
    {
        return aggregate("number-per-type");
    }

}
