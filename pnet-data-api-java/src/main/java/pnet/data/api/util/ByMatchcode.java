package pnet.data.api.util;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Executes a get call by matchcode
 *
 * @author cet
 * @param <DTO> the type of DTO
 * @param <SELF> the type of the restrict for chaining
 */
public interface ByMatchcode<DTO, SELF extends By<DTO, SELF>> extends By<DTO, SELF>
{
    default DTO byMatchcode(String matchcode) throws PnetDataClientException
    {
        return allByMatchcodes(Arrays.asList(matchcode), 0, 1).first();
    }

    default PnetDataClientResultPage<DTO> allByMatchcodes(List<String> matchcodes, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return restrict("mc", matchcodes).execute(pageIndex, itemsPerPage);
    }

}
