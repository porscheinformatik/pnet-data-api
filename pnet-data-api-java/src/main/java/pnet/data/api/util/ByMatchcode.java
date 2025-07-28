package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.util.Collections;
import java.util.List;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Executes a get call by matchcode
 *
 * @param <DTO> the type of DTO
 * @param <SELF> the type of the restrict for chaining
 * @author cet
 */
public interface ByMatchcode<DTO, SELF extends By<DTO, SELF>> extends By<DTO, SELF> {
    default DTO byMatchcode(String matchcode) throws PnetDataClientException {
        return allByMatchcodes(Collections.singletonList(matchcode), 0, 1).first();
    }

    default PnetDataClientResultPage<DTO> allByMatchcodes(List<String> matchcodes, int pageIndex, int itemsPerPage)
        throws PnetDataClientException {
        return restrict(MATCHCODE_KEY, matchcodes).execute(pageIndex, itemsPerPage);
    }
}
