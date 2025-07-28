package pnet.data.api.util;

/**
 * Aggregates the number of items per category.
 *
 * @param <SELF> the type of the aggregate for chaining
 * @author ham
 */
public interface AggregateNumberPerCategory<SELF extends Aggregate<SELF>> extends Aggregate<SELF> {
    default SELF aggregateNumberPerCategory() {
        return aggregate("number-per-category");
    }
}
