package pnet.data.api.util;

import java.util.List;

import pnet.data.api.PnetDataApiException;
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

    PnetDataClientResultPage<DTO> search(String language, String query, List<Pair<String, Object>> filters,
        int pageIndex, int itemsPerPage) throws PnetDataApiException;

}
