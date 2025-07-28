package pnet.data.api.client;

import pnet.data.api.ResultPage;
import pnet.data.api.ResultPageWithAggregations;

/**
 * A {@link ResultPage} with a link to the next page
 *
 * @param <T> the type of items
 * @param <AggregationsT> the type of aggregations
 * @author ham
 */
public interface PnetDataClientResultPageWithAggregations<T, AggregationsT>
    extends PnetDataClientResultPage<T>, ResultPageWithAggregations<T, AggregationsT> {
    // intentionally left blank
}
