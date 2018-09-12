package pnet.data.api.util;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Function for scrolling
 *
 * @author ham
 * @param <DTO> the DTO
 */
public interface NextFunction<DTO>
{

    PnetDataClientResultPage<DTO> next(String scrollId) throws PnetDataClientException;

}
