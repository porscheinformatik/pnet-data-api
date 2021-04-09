package pnet.data.api.util;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A find query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 */
public interface Get<DTO>
{
    default DTO firstOnly() throws PnetDataClientException
    {
        return execute(0, 1).first();
    }

    PnetDataClientResultPage<DTO> execute(int pageIndex, int itemsPerPage) throws PnetDataClientException;
}
