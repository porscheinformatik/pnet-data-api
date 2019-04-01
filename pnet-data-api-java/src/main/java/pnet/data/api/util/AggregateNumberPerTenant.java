package pnet.data.api.util;

/**
 * Aggregates the number of items per tenant.
 *
 * @author ham
 * @param <SELF> the type of the aggregate for chaining
 */
public interface AggregateNumberPerTenant<SELF extends Aggregate<SELF>> extends Aggregate<SELF>
{

    default SELF aggregateNumberPerTenant()
    {
        return aggregate("number-per-tenant");
    }

}
