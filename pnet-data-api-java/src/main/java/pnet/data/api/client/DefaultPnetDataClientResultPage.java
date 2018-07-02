package pnet.data.api.client;

import java.util.Collections;
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

    private static final long serialVersionUID = 7087221061204249715L;

    public static <T> DefaultPnetDataClientResultPage<T> of(ResultPage<T> resultPage)
    {
        return new DefaultPnetDataClientResultPage<>(resultPage.getItems(), resultPage.getItemsPerPage(),
            resultPage.getTotalNumberOfItems(), resultPage.getPageIndex(), resultPage.getNumberOfPages());
    }

    private final List<T> items;
    private final int itemsPerPage;
    private final int totalNumberOfItems;
    private final int pageIndex;
    private final int numberOfPages;

    private PnetDataClientPageSupplier<T> pageSupplier;

    public DefaultPnetDataClientResultPage(@JsonProperty("items") List<T> items,
        @JsonProperty("itemsPerPage") int itemsPerPage, @JsonProperty("totalNumberOfItems") int totalNumberOfItems,
        @JsonProperty("pageIndex") int pageIndex, @JsonProperty("numberOfPages") int numberOfPages)
    {
        super();

        this.items = items;
        this.itemsPerPage = itemsPerPage;
        this.totalNumberOfItems = totalNumberOfItems;
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

    public PnetDataClientPageSupplier<T> getPageSupplier()
    {
        return pageSupplier;
    }

    public void setPageSupplier(PnetDataClientPageSupplier<T> pageSupplier)
    {
        this.pageSupplier = pageSupplier;
    }

    /**
     * @return the next page. Executes a call if there is another page. Never null, but an empty page.
     * @throws PnetDataClientException on occasion
     */
    @Override
    public PnetDataClientResultPage<T> nextPage() throws PnetDataClientException
    {
        if (!hasNextPage())
        {
            return new DefaultPnetDataClientResultPage<>(Collections.emptyList(), itemsPerPage, totalNumberOfItems,
                pageIndex + 1, numberOfPages);
        }

        return pageSupplier.get(pageIndex + 1);
    }

    @Override
    public PnetDataClientResultPage<T> getPage(int index) throws PnetDataClientException
    {
        if (pageSupplier == null)
        {
            throw new UnsupportedOperationException("Feature not implemented");
        }

        return pageSupplier.get(index);
    }

}
