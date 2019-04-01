package pnet.data.api.util;

/**
 * Aggregates the number of items per company.
 *
 * @author ham
 * @param <SELF> the type of the aggregate for chaining
 */
public interface AggregateNumberPerCompany<SELF extends Aggregate<SELF>> extends Aggregate<SELF>
{

    default SELF aggregateNumberPerCompany()
    {
        return aggregate("number-per-company");
    }

}
