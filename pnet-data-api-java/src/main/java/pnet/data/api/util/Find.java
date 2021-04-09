package pnet.data.api.util;

import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A find query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 */
public interface Find<DTO>
{
    default DTO firstOnly(Locale language) throws PnetDataClientException
    {
        return execute(language, 0, 1).first();
    }

    default PnetDataClientResultPage<DTO> execute(Locale language) throws PnetDataClientException
    {
        return execute(language, 0, 10);
    }

    PnetDataClientResultPage<DTO> execute(Locale language, int pageIndex, int itemsPerPage)
        throws PnetDataClientException;
}
