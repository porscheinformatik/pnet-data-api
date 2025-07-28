package pnet.data.api.util;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A find query.
 *
 * @param <DTO> the type of DTO
 * @author ham
 */
public interface ScrollableGet<DTO> extends Get<DTO> {
    PnetDataClientResultPage<DTO> executeAndScroll(int itemsPerPage) throws PnetDataClientException;
}
