package pnet.data.api.util;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;

/**
 * Function for search.
 *
 * @author ham
 * @param <DTO> the DTO
 * @param <AggregationsDTO> the type of aggregations DTO
 */
@FunctionalInterface
public interface SearchWithAggregationsFunction<DTO, AggregationsDTO> extends SearchFunction<DTO>
{

    @Override
    PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException;

}
