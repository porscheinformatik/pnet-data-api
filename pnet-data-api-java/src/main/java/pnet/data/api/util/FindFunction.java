package pnet.data.api.util;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Function for find
 *
 * @author ham
 * @param <DTO> the DTO
 */
@FunctionalInterface
public interface FindFunction<DTO>
{

    PnetDataClientResultPage<DTO> find(Locale language, List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataApiException;

}
