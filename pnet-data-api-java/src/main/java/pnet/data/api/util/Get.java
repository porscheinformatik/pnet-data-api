package pnet.data.api.util;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.SearchAfter;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A find query.
 *
 * @param <DTO> the type of DTO
 * @author ham
 */
public interface Get<DTO>
{
    DTO firstOnly() throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute() throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute(SearchAfter searchAfter, int itemsPerPage) throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute(int pageIndex, int itemsPerPage) throws PnetDataClientException;
}
