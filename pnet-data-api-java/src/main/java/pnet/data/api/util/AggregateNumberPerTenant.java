package pnet.data.api.util;

/**
 * Aggregates the number of items per tenant.
 *
 * @param <SELF> the type of the aggregate for chaining
 * @author ham
 */
public interface AggregateNumberPerTenant<SELF extends Aggregate<SELF>> extends Aggregate<SELF> {
    default SELF aggregateNumberPerTenant() {
        return aggregate("number-per-tenant");
    }
}
