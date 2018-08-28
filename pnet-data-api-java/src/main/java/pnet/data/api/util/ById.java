package pnet.data.api.util;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Executes a get call by id
 *
 * @author cet
 * @param <DTO> the type of DTO
 * @param <SELF> the type of the restrict for chaining
 */
public interface ById<DTO, SELF extends By<DTO, SELF>> extends By<DTO, SELF>
{

    default DTO byId(Integer id) throws PnetDataClientException
    {
        return allByIds(Arrays.asList(id), 0, 1).first();
    }

    default PnetDataClientResultPage<DTO> allByIds(List<Integer> ids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return execute("id", ids, pageIndex, itemsPerPage);
    }

}
