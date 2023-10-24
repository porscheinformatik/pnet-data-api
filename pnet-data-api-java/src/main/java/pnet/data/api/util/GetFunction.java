package pnet.data.api.util;

import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Function for get
 *
 * @author ham
 * @param <DTO> the DTO
 */
@FunctionalInterface
public interface GetFunction<DTO>
{
    PnetDataClientResultPage<DTO> get(List<Pair<String, Object>> restricts) throws PnetDataClientException;
}
