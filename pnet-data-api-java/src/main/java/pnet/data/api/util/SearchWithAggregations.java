package pnet.data.api.util;

import java.util.Locale;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.SearchAfter;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;

/**
 * A search query.
 *
 * @param <DTO> the type of DTO
 * @param <AggregationsDTO> the type of aggregations DTO
 * @author ham
 */
public interface SearchWithAggregations<DTO, AggregationsDTO> extends Search<DTO> {
    @Override
    DTO firstOnly(Locale language, String query) throws PnetDataClientException;

    @Override
    PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(Locale language, String query)
        throws PnetDataClientException;

    @Override
    PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(
        Locale language,
        String query,
        SearchAfter searchAfter,
        int itemsPerPage
    ) throws PnetDataClientException;

    @Override
    PnetDataClientResultPageWithAggregations<DTO, AggregationsDTO> execute(
        Locale language,
        String query,
        int pageIndex,
        int itemsPerPage
    ) throws PnetDataClientException;
}
