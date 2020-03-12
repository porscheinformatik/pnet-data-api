package pnet.data.api.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.ResultPage;

/**
 * A {@link ResultPage} with a link to the next page
 *
 * @author ham
 * @param <T> the type of items
 */
public class DefaultPnetDataClientResultPage<T> implements PnetDataClientResultPage<T>
{

    public static <T> DefaultPnetDataClientResultPage<T> of(ResultPage<T> resultPage)
    {
        return new DefaultPnetDataClientResultPage<>(resultPage.getItems(), resultPage.getItemsPerPage(),
            resultPage.getTotalNumberOfItems(), resultPage.getPageIndex(), resultPage.getNumberOfPages(),
            resultPage.getScrollId());
    }

    private final List<T> items;
    private final int itemsPerPage;
    private final int totalNumberOfItems;
    private final int pageIndex;
    private final int numberOfPages;
    private final String scrollId;

    private PnetDataClientPageSupplier<T> pageSupplier;
    private PnetDataClientScrollSupplier<T> scrollSupplier;

    public DefaultPnetDataClientResultPage(@JsonProperty("items") List<T> items,
        @JsonProperty("itemsPerPage") int itemsPerPage, @JsonProperty("totalNumberOfItems") int totalNumberOfItems,
        @JsonProperty("pageIndex") int pageIndex, @JsonProperty("numberOfPages") int numberOfPages,
        @JsonProperty("scrollId") String scrollId)
    {
        super();

        this.items = items;
        this.itemsPerPage = itemsPerPage;
        this.totalNumberOfItems = totalNumberOfItems;
        this.pageIndex = pageIndex;
        this.numberOfPages = numberOfPages;
        this.scrollId = scrollId;
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
    public int getTotalNumberOfItems()
    {
        return totalNumberOfItems;
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

    @Override
    public String getScrollId()
    {
        return scrollId;
    }

    public PnetDataClientPageSupplier<T> getPageSupplier()
    {
        return pageSupplier;
    }

    public void setPageSupplier(PnetDataClientPageSupplier<T> pageSupplier)
    {
        this.pageSupplier = pageSupplier;
    }

    public PnetDataClientScrollSupplier<T> getScrollSupplier()
    {
        return scrollSupplier;
    }

    public void setScrollSupplier(PnetDataClientScrollSupplier<T> scrollSupplier)
    {
        this.scrollSupplier = scrollSupplier;
    }

    @Override
    public PnetDataClientResultPage<T> nextPage() throws PnetDataClientException
    {
        if (!hasNextPage())
        {
            return null;
        }

        PnetDataClientResultPage<T> result;

        if (scrollId != null && scrollSupplier != null)
        {
            result = scrollSupplier.get(scrollId);
        }
        else
        {
            result = pageSupplier.get(pageIndex + 1);
        }

        if (result != null && result.size() == 0)
        {
            return null;
        }

        return result;
    }

    @Override
    public PnetDataClientResultPage<T> getPage(int index) throws PnetDataClientException
    {
        if (pageSupplier == null)
        {
            throw new UnsupportedOperationException("Feature not supported");
        }

        PnetDataClientResultPage<T> result = pageSupplier.get(index);

        if (result != null && result.size() == 0)
        {
            return null;
        }

        return result;
    }

}
