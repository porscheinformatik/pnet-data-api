package pnet.data.api.util;

/**
 * Aggregates the number of items per contract type.
 *
 * @param <SELF> the type of the aggregate for chaining
 * @author ham
 */
public interface AggregateNumberPerContractType<SELF extends Aggregate<SELF>> extends Aggregate<SELF> {
    default SELF aggregateNumberPerContractType() {
        return aggregate("number-per-contract-type");
    }
}
