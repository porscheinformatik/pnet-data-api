package pnet.data.api.util;

/**
 * Aggregates the number of items per brand.
 *
 * @param <SELF> the type of the aggregate for chaining
 * @author ham
 */
public interface AggregateNumberPerBrand<SELF extends Aggregate<SELF>> extends Aggregate<SELF> {
    default SELF aggregateNumberPerBrand() {
        return aggregate("number-per-brand");
    }
}
