package pnet.data.api.client;

import pnet.data.api.ResultPage;
import pnet.data.api.ResultPageWithAggregates;

/**
 * A {@link ResultPage} with a link to the next page
 *
 * @author ham
 * @param <T> the type of items
 * @param <AggregatesT> the type of aggregates
 */
public interface PnetDataClientResultPageWithAggregates<T, AggregatesT>
    extends PnetDataClientResultPage<T>, ResultPageWithAggregates<T, AggregatesT>
{

    // intentionally left blank

}
