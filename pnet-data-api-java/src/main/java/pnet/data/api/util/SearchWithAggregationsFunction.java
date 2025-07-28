package pnet.data.api.util;

import java.util.List;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;

/**
 * Function for search.
 *
 * @param <DTO> the DTO
 * @param <AggregationsDTO> the type of aggregations DTO
 * @author ham
 */
@FunctionalInterface
public interface SearchWithAggregationsFunction<DTO, AggregationsDTO> extends SearchFunction<DTO> {
    @Override
    PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException;
}
