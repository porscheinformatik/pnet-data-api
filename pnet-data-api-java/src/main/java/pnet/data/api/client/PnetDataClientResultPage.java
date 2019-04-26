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
 * @author ham
 * @param <T> the type of items
 */
public interface PnetDataClientResultPage<T> extends ResultPage<T>
{

    /**
     * @return a stream of the items of this and the subsequent pages (dynamically calls {@link #nextPage()}
     */
    default Stream<T> streamAll()
    {
        return StreamSupport.stream(new PnetDataClientPageSpliterator<>(this), false);
    }

    /**
     * @return an iterator of all items of this and the subsequent pages (dynamically calls {@link #nextPage()}
     */
    default Iterator<T> iteratorAll()
    {
        return Spliterators.iterator(new PnetDataClientPageSpliterator<>(this));
    }

    /**
     * @return the next page, null if there is no next page
     * @throws PnetDataClientException on occasion
     */
    PnetDataClientResultPage<T> nextPage() throws PnetDataClientException;

    /**
     * @param index the index of the page, 0-based
     * @return the specified page
     * @throws PnetDataClientException on occasion
     */
    PnetDataClientResultPage<T> getPage(int index) throws PnetDataClientException;

}
