package pnet.data.api.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.ResultPage;

/**
 * A {@link ResultPage} with a link to the next page
 *
 * @author ham
 * @param <T> the type of items
 * @param <AggregatesT> the type of the aggregates object
 */
public class DefaultPnetDataClientResultPageWithAggregates<T, AggregatesT> extends DefaultPnetDataClientResultPage<T>
    implements PnetDataClientResultPageWithAggregates<T, AggregatesT>
{

    private static final long serialVersionUID = 860019410295484359L;

    private final AggregatesT aggregates;

    public DefaultPnetDataClientResultPageWithAggregates(@JsonProperty("items") List<T> items,
        @JsonProperty("aggregates") AggregatesT aggregates, @JsonProperty("itemsPerPage") int itemsPerPage,
        @JsonProperty("totalNumberOfItems") int totalNumberOfItems, @JsonProperty("pageIndex") int pageIndex,
        @JsonProperty("numberOfPages") int numberOfPages, @JsonProperty("scrollId") String scrollId)
    {
        super(items, itemsPerPage, totalNumberOfItems, pageIndex, numberOfPages, scrollId);

        this.aggregates = aggregates;
    }

    @Override
    public AggregatesT getAggregates()
    {
        return aggregates;
    }
}
