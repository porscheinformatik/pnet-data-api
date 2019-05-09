package pnet.data.api.util;

import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;

/**
 * A search query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 * @param <AggregationsDTO> the type of aggregations DTO
 */
public interface SearchWithAggregations<DTO, AggregationsDTO> extends Search<DTO>
{
    @Override
    default PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(Locale language, String query)
        throws PnetDataClientException
    {
        return execute(language, query, 0, 10);
    }

    @Override
    PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(Locale language, String query, int pageIndex,
        int itemsPerPage) throws PnetDataClientException;

}
