package pnet.data.api.util;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPageWithAggregates;

/**
 * Function for search.
 *
 * @author ham
 * @param <DTO> the DTO
 * @param <AggregatesDTO> the type of aggregates DTO
 */
@FunctionalInterface
public interface SearchWithAggregatesFunction<DTO, AggregatesDTO> extends SearchFunction<DTO>
{

    @Override
    PnetDataClientResultPageWithAggregates<DTO, AggregatesDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException;

}
