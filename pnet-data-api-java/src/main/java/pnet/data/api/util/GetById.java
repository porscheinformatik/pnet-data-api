package pnet.data.api.util;

import java.util.Arrays;
import java.util.List;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Returns data by id
 *
 * @param <T> the type of data
 * @author ham
 */
public interface GetById<T> {
    default T getById(Integer id) throws PnetDataClientException {
        return getAllByIds(Arrays.asList(id), 0, 1).first();
    }

    PnetDataClientResultPage<T> getAllByIds(List<Integer> ids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException;
}
