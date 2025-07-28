package pnet.data.api.util;

import java.util.Locale;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.SearchAfter;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A search query.
 *
 * @param <DTO> the type of DTO
 * @author ham
 */
public interface Search<DTO> {
    DTO firstOnly(Locale language, String query) throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute(Locale language, String query) throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute(Locale language, String query, SearchAfter searchAfter, int itemsPerPage)
        throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute(Locale language, String query, int pageIndex, int itemsPerPage)
        throws PnetDataClientException;
}
