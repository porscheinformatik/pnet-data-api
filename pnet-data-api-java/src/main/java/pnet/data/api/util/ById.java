package pnet.data.api.util;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Executes a get call by id
 *
 * @author cet
 * @param <IdT> the type of id
 * @param <DTO> the type of DTO
 * @param <SELF> the type of the restrict for chaining
 */
public interface ById<IdT, DTO, SELF extends By<DTO, SELF>> extends By<DTO, SELF>
{
    default DTO byId(IdT id) throws PnetDataClientException
    {
        return allByIds(Arrays.asList(id), 0, 1).first();
    }

    default PnetDataClientResultPage<DTO> allByIds(List<IdT> ids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return restrict("id", ids).execute(pageIndex, itemsPerPage);
    }
}
