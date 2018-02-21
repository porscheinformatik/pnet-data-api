package pnet.data.api.util;

import java.util.Locale;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A search query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 */
public interface Search<DTO>
{
    default PnetDataClientResultPage<DTO> execute(Locale language, String query) throws PnetDataApiException
    {
        return execute(language, query, 0, 10);
    }

    PnetDataClientResultPage<DTO> execute(Locale language, String query, int pageIndex, int itemsPerPage)
        throws PnetDataApiException;

}
