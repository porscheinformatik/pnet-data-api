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
     * Returns the next page, uses the searchAfter feature. By setting the system property {@link #AVOID_SEARCH_AFTER_PROPERTY_KEY}
     * to "true" you can avoid using the searchAfter feature.
     *
     * @return the next page, null if there is no next page
     * @throws PnetDataClientException on occasion
     */
    PnetDataClientResultPage<T> nextPage() throws PnetDataClientException;
}
