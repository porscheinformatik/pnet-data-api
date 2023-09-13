package pnet.data.api.util;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a search query.
 *
 * @author ham
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the filter itself for fluent interface
 */
public abstract class AbstractScrollableSearch<DTO, SELF extends AbstractScrollableSearch<DTO, SELF>>
    extends AbstractSearch<DTO, SELF> implements ScrollableSearch<DTO>
{
    protected AbstractScrollableSearch(SearchFunction<DTO> searchFunction, List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }

    @Override
    public PnetDataClientResultPage<DTO> executeAndScroll(Locale language, String query, int itemsPerPage)
        throws PnetDataClientException
    {
        return restrict("scroll", true).execute(language, query, 0, itemsPerPage);
    }
}
