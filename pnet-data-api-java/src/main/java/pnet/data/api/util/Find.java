package pnet.data.api.util;

import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.SearchAfter;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A find query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 */
public interface Find<DTO>
{
    DTO firstOnly(Locale language) throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute(Locale language) throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute(Locale language, SearchAfter searchAfter, int itemsPerPage)
        throws PnetDataClientException;

    PnetDataClientResultPage<DTO> execute(Locale language, int pageIndex, int itemsPerPage)
        throws PnetDataClientException;
}
