package pnet.data.api.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.ResultPage;
import pnet.data.api.SearchAfter;
import pnet.data.api.util.Pair;

/**
 * A {@link ResultPage} with a link to the next page
 *
 * @author ham
 * @param <T> the type of items
 */
public class DefaultPnetDataClientResultPage<T> implements PnetDataClientResultPage<T>
{
    @SuppressWarnings("deprecation")
    public static <T> DefaultPnetDataClientResultPage<T> of(ResultPage<T> resultPage)
    {
        return new DefaultPnetDataClientResultPage<>(resultPage.getItems(), resultPage.getItemsPerPage(),
            resultPage.getTotalNumberOfItems(), resultPage.getPageIndex(), resultPage.getNumberOfPages(),
            resultPage.getSearchAfter(), resultPage.getScrollId());
    }

    private final List<T> items;
    private final int itemsPerPage;
    private final int totalNumberOfItems;
    private final int pageIndex;
    private final int numberOfPages;
    private final SearchAfter searchAfter;
    private final String scrollId;

    private List<Pair<String, Object>> restricts;
    private PnetDataClientPageSupplier<T> pageSupplier;
    private PnetDataClientScrollSupplier<T> scrollSupplier;

    public DefaultPnetDataClientResultPage(@JsonProperty("items") List<T> items,
        @JsonProperty("itemsPerPage") int itemsPerPage, @JsonProperty("totalNumberOfItems") int totalNumberOfItems,
        @JsonProperty("pageIndex") int pageIndex, @JsonProperty("numberOfPages") int numberOfPages,
        @JsonProperty("searchAfter") SearchAfter searchAfter, @JsonProperty("scrollId") String scrollId)
    {
        super();

        this.items = items;
        this.itemsPerPage = itemsPerPage;
        this.totalNumberOfItems = totalNumberOfItems;
        this.pageIndex = pageIndex;
        this.numberOfPages = numberOfPages;
        this.searchAfter = searchAfter;
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
    @Deprecated
    public int getPageIndex()
    {
        return pageIndex;
    }

    @Override
    @Deprecated
    public int getNumberOfPages()
    {
        return numberOfPages;
    }

    @Override
    public SearchAfter getSearchAfter()
    {
        return searchAfter;
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

    public void setPageSupplier(List<Pair<String, Object>> restricts, PnetDataClientPageSupplier<T> pageSupplier)
    {
        this.restricts = restricts;
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
    public PnetDataClientResultPage<T> nextPage(boolean avoidSearchAfter) throws PnetDataClientException
    {
        if (isEmpty())
        {
            return null;
        }

        PnetDataClientResultPage<T> result;

        if (scrollId != null && scrollSupplier != null)
        {
            result = scrollSupplier.get(scrollId);
        }
        else if (!avoidSearchAfter && searchAfter != null)
        {
            List<Pair<String, Object>> restricts = new ArrayList<>(this.restricts);

            restricts.removeIf(restrict -> "p".equals(restrict.getLeft()));
            restricts.removeIf(restrict -> "sa".equals(restrict.getLeft()));
            restricts.add(Pair.of("p", pageIndex + 1)); // for backward compatibility
            restricts.add(Pair.of("sa", searchAfter.getValue()));

            result = pageSupplier.get(restricts);
        }
        else
        {
            List<Pair<String, Object>> restricts = new ArrayList<>(this.restricts);

            restricts.removeIf(restrict -> "p".equals(restrict.getLeft()));
            restricts.removeIf(restrict -> "sa".equals(restrict.getLeft()));
            restricts.add(Pair.of("p", pageIndex + 1));

            result = pageSupplier.get(restricts);
        }

        return result;
    }

    @Override
    @Deprecated
    public PnetDataClientResultPage<T> getPage(int index) throws PnetDataClientException
    {
        if (pageSupplier == null)
        {
            throw new UnsupportedOperationException("Feature not supported");
        }

        List<Pair<String, Object>> restricts = new ArrayList<>(this.restricts);

        restricts.removeIf(restrict -> "p".equals(restrict.getLeft()));
        restricts.removeIf(restrict -> "sa".equals(restrict.getLeft()));
        restricts.add(Pair.of("p", pageIndex + 1));

        return pageSupplier.get(restricts);
    }
}
