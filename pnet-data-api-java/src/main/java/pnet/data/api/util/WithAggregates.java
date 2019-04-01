package pnet.data.api.util;

/**
 * Provides an aggregation result
 *
 * @author HAM
 * @param <AggregatesT> the type of aggregates collector object
 */
public interface WithAggregates<AggregatesT>
{

    /**
     * Returns the aggregations, null if not available
     *
     * @return the aggregations
     */
    AggregatesT getAggregates();

}
