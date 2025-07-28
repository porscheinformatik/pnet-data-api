package pnet.data.api.util;

import java.util.Locale;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A search query.
 *
 * @param <DTO> the type of DTO
 * @author ham
 */
public interface ScrollableSearch<DTO> extends Search<DTO> {
    PnetDataClientResultPage<DTO> executeAndScroll(Locale language, String query, int itemsPerPage)
        throws PnetDataClientException;
}
