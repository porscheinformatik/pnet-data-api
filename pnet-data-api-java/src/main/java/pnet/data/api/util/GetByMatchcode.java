package pnet.data.api.util;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Returns data by matchcode
 *
 * @author ham
 * @param <T> the type of data
 */
public interface GetByMatchcode<T>
{

    default T getByMatchcode(String matchcode) throws PnetDataClientException
    {
        return getAllByMatchcodes(Arrays.asList(matchcode), 0, 1).first();
    }

    PnetDataClientResultPage<T> getAllByMatchcodes(List<String> matchcodes, int pageIndex, int itemsPerPage)
        throws PnetDataClientException;

}
