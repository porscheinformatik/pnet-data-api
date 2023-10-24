package pnet.data.api.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.ResultPage;
import pnet.data.api.SearchAfter;

/**
 * A {@link ResultPage} with a link to the next page
 *
 * @author ham
 * @param <T> the type of items
 * @param <AggregationsT> the type of the aggregation object
 */
public class DefaultPnetDataClientResultPageWithAggregations<T, AggregationsT>
    extends DefaultPnetDataClientResultPage<T> implements PnetDataClientResultPageWithAggregations<T, AggregationsT>
{
    private final AggregationsT aggregations;

    public DefaultPnetDataClientResultPageWithAggregations(@JsonProperty("items") List<T> items,
        @JsonProperty("aggregations") AggregationsT aggregations, @JsonProperty("itemsPerPage") int itemsPerPage,
        @JsonProperty("totalNumberOfItems") int totalNumberOfItems, @JsonProperty("pageIndex") int pageIndex,
        @JsonProperty("numberOfPages") int numberOfPages, @JsonProperty("searchAfter") SearchAfter searchAfter,
        @JsonProperty("scrollId") String scrollId)
    {
        super(items, itemsPerPage, totalNumberOfItems, pageIndex, numberOfPages, searchAfter, scrollId);

        this.aggregations = aggregations;
    }

    @Override
    public AggregationsT getAggregations()
    {
        return aggregations;
    }
}
