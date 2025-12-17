package pnet.data.api.client;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.ResultPage;

/**
 * A {@link ResultPage} with a link to the next page
 *
 * @param <T> the type of items
 * @author ham
 */
public interface PnetDataClientResultPage<T> extends ResultPage<T> {
    String AVOID_SEARCH_AFTER_PROPERTY_KEY = "pnet-data-api.avoid-search-after";

    /**
     * @return a stream of the items of this and the subsequent pages (dynamically calls {@link #nextPage()}
     */
    default Stream<T> streamAll() {
        return StreamSupport.stream(new PnetDataClientPageSpliterator<>(this), false);
    }

    /**
     * @return an iterator of all items of this and the subsequent pages (dynamically calls {@link #nextPage()}
     */
    default Iterator<T> iteratorAll() {
        return Spliterators.iterator(new PnetDataClientPageSpliterator<>(this));
    }

    /**
     * @return the next page, null if there is no next page
     * @throws PnetDataClientException on occasion
     */
    default PnetDataClientResultPage<T> nextPage() throws PnetDataClientException {
        String avoidSearchAfterProperty = System.getProperty(PnetDataClientResultPage.AVOID_SEARCH_AFTER_PROPERTY_KEY);
        boolean avoidSearchAfter =
            "".equals(avoidSearchAfterProperty) || "true".equalsIgnoreCase(avoidSearchAfterProperty);

        return nextPage(avoidSearchAfter);
    }

    /**
     * @param avoidSearchAfter set to true in order to search by using the page index instead of the searchAfter
     * property
     * @return the next page, null if there is no next page
     * @throws PnetDataClientException on occasion
     * @deprecated this method is only supported in order to provide backward compatibility. If the next page will be
     * loaded by using the searchAfter property (which is the default), the pageIndex will not be filled correctly due
     * to restriction of our document store. You can modify the default behavior by setting the system property
     * {@link #AVOID_SEARCH_AFTER_PROPERTY_KEY} to "true".
     */
    @Deprecated
    PnetDataClientResultPage<T> nextPage(boolean avoidSearchAfter) throws PnetDataClientException;

    /**
     * @param index the index of the page, 0-based
     * @return the specified page
     * @throws PnetDataClientException on occasion
     * @deprecated PageIndex properties will be removed in future, this method will not be supported anymore (see
     * {@link ResultPage#getPageIndex()} for more information).
     */
    @Deprecated
    PnetDataClientResultPage<T> getPage(int index) throws PnetDataClientException;
}
