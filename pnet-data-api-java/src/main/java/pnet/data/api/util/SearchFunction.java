package pnet.data.api.util;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Function for search.
 *
 * @author ham
 * @param <DTO> the DTO
 */
@FunctionalInterface
public interface SearchFunction<DTO>
{

    PnetDataClientResultPage<DTO> search(Locale language, String query, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException;

}
