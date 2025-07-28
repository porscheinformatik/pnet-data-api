package pnet.data.api.util;

import java.util.List;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Function for search.
 *
 * @param <DTO> the DTO
 * @author ham
 */
@FunctionalInterface
public interface SearchFunction<DTO> {
    PnetDataClientResultPage<DTO> search(List<Pair<String, Object>> restricts) throws PnetDataClientException;
}
