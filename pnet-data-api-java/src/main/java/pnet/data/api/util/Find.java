package pnet.data.api.util;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A find query.
 *
 * @author ham
 * @param <T> the type of DTO
 */
public interface Find<T>
{

    PnetDataClientResultPage<T> execute(String language, int pageIndex, int itemsPerPage) throws PnetDataApiException;

}
