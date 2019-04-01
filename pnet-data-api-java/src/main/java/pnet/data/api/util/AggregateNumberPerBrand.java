package pnet.data.api.util;

/**
 * Aggregates the number of items per brand.
 *
 * @author ham
 * @param <SELF> the type of the aggregate for chaining
 */
public interface AggregateNumberPerBrand<SELF extends Aggregate<SELF>> extends Aggregate<SELF>
{

    default SELF aggregateNumberPerBrand()
    {
        return aggregate("number-per-brand");
    }

}
