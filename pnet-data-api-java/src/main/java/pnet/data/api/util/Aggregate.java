package pnet.data.api.util;

/**
 * An aggregation for Data-API queries
 *
 * @author ham
 * @param <SELF> the type of the aggregation, for chaining
 */
@FunctionalInterface
public interface Aggregate<SELF extends Aggregate<SELF>>
{
    SELF aggregate(String name);
}
