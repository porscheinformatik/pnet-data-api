package pnet.data.api.util;

/**
 * Aggregates the number of items per function.
 *
 * @param <SELF> the type of the aggregate for chaining
 * @author ham
 */
public interface AggregateNumberPerFunction<SELF extends Aggregate<SELF>> extends Aggregate<SELF> {
    default SELF aggregateNumberPerFunction() {
        return aggregate("number-per-function");
    }
}
