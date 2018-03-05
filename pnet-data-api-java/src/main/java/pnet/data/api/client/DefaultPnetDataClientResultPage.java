package pnet.data.api.client;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.ResultPage;

/**
 * A {@link ResultPage} with a link to the next page
 *
 * @author ham
 * @param <T> the type of items
 */
public class DefaultPnetDataClientResultPage<T> implements PnetDataClientResultPage<T>
{

    private static final long serialVersionUID = 7087221061204249715L;

    public static <T> DefaultPnetDataClientResultPage<T> of(ResultPage<T> resultPage)
    {
        return new DefaultPnetDataClientResultPage<>(resultPage.getItems(), resultPage.getItemsPerPage(),
            resultPage.getNumberOfItems(), resultPage.getPageIndex(), resultPage.getNumberOfPages());
    }

    private final List<T> items;
    private final int itemsPerPage;
    private final int numberOfItems;
    private final int pageIndex;
    private final int numberOfPages;

    private PnetDataClientNextPageSupplier<T> nextPageSupplier;

    public DefaultPnetDataClientResultPage(@JsonProperty("items") List<T> items,
        @JsonProperty("itemsPerPage") int itemsPerPage, @JsonProperty("numberOfItems") int numberOfItems,
        @JsonProperty("pageIndex") int pageIndex, @JsonProperty("numberOfPages") int numberOfPages)
    {
        super();

        this.items = items;
        this.itemsPerPage = itemsPerPage;
        this.numberOfItems = numberOfItems;
        this.pageIndex = pageIndex;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public List<T> getItems()
    {
        return items;
    }

    @Override
    public int getItemsPerPage()
    {
        return itemsPerPage;
    }

    @Override
    public int getNumberOfItems()
    {
        return numberOfItems;
    }

    @Override
    public int getPageIndex()
    {
        return pageIndex;
    }

    @Override
    public int getNumberOfPages()
    {
        return numberOfPages;
    }

    public PnetDataClientNextPageSupplier<T> getNextPageSupplier()
    {
        return nextPageSupplier;
    }

    public void setNextPageSupplier(PnetDataClientNextPageSupplier<T> nextPageSupplier)
    {
        this.nextPageSupplier = nextPageSupplier;
    }

    /**
     * @return the next page. Executes a call if there is another page. Never null, but an empty page.
     * @throws PnetDataApiException
     */
    @Override
    public PnetDataClientResultPage<T> nextPage() throws PnetDataApiException
    {
        if (nextPageSupplier == null || !hasNextPage())
        {
            return new DefaultPnetDataClientResultPage<>(Collections.emptyList(), itemsPerPage, numberOfItems,
                pageIndex + 1, numberOfPages);
        }

        return nextPageSupplier.get();
    }

}
