package pnet.data.api.util;

import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPageWithAggregates;

/**
 * A search query.
 *
 * @author ham
 * @param <DTO> the type of DTO
 * @param <AggregatesDTO> the type of aggregates DTO
 */
public interface SearchWithAggregates<DTO, AggregatesDTO> extends Search<DTO>
{
    @Override
    default PnetDataClientResultPageWithAggregates<DTO, AggregatesDTO> execute(Locale language, String query)
        throws PnetDataClientException
    {
        return execute(language, query, 0, 10);
    }

    @Override
    PnetDataClientResultPageWithAggregates<DTO, AggregatesDTO> execute(Locale language, String query, int pageIndex,
        int itemsPerPage) throws PnetDataClientException;

}
