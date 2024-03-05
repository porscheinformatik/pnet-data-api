package pnet.data.api.util;

/**
 * An aggregation for Data-API queries
 *
 * @param <SELF> the type of the aggregation, for chaining
 * @author ham
 */
@FunctionalInterface
public interface Aggregate<SELF extends Aggregate<SELF>>
{
    SELF aggregate(String name);
}
