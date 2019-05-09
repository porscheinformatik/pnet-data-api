package pnet.data.api.util;

/**
 * Provides an aggregation result
 *
 * @author HAM
 * @param <AggregationsT> the type of aggregations collector object
 */
public interface WithAggregations<AggregationsT>
{

    /**
     * Returns the aggregations, null if not available
     *
     * @return the aggregations
     */
    AggregationsT getAggregations();

}
