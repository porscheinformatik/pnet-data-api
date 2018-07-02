package pnet.data.api.client;

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
     * @return the next page. Executes a call if there is another page. Never null, but an empty page.
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
