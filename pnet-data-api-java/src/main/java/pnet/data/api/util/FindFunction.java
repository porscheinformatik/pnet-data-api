package pnet.data.api.util;

import java.util.List;

import pnet.data.api.PnetDataClientException;
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
    PnetDataClientResultPage<DTO> find(List<Pair<String, Object>> restricts) throws PnetDataClientException;
}
