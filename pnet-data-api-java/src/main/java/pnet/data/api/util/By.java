package pnet.data.api.util;

import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * A filter for Data-API queries
 *
 * @author ham
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the filter, for chaining
 */
public interface By<DTO, SELF extends By<DTO, SELF>> extends Restrict<SELF>
{

    PnetDataClientResultPage<DTO> execute(String parameterName, List<?> parameterValues, int pageIndex,
        int itemsPerPage) throws PnetDataClientException;

}
