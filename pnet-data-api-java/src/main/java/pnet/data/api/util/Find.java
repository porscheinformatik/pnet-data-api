package pnet.data.api.util;

import java.util.Locale;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A find query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 */
public interface Find<DTO>
{

    default PnetDataClientResultPage<DTO> execute(Locale language) throws PnetDataApiException
    {
        return execute(language, 0, 10);
    }

    PnetDataClientResultPage<DTO> execute(Locale language, int pageIndex, int itemsPerPage) throws PnetDataApiException;

}
