package pnet.data.api.util;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A search query.
 *
 * @author ham
 * @param <T> the type of DTO
 */
public interface Search<T>
{

    PnetDataClientResultPage<T> execute(String language, String query, int pageIndex, int itemsPerPage)
        throws PnetDataApiException;

}
