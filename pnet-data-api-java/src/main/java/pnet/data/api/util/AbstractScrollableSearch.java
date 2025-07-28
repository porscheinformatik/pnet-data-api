package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.util.List;
import java.util.Locale;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a search query.
 *
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the filter itself for fluent interface
 * @author ham
 */
@SuppressWarnings("deprecation")
public abstract class AbstractScrollableSearch<DTO, SELF extends AbstractScrollableSearch<DTO, SELF>>
    extends AbstractSearch<DTO, SELF>
    implements ScrollableSearch<DTO>, Scrollable<SELF> {

    protected AbstractScrollableSearch(SearchFunction<DTO> searchFunction, List<Pair<String, Object>> restricts) {
        super(searchFunction, restricts);
    }

    @Override
    public PnetDataClientResultPage<DTO> executeAndScroll(Locale language, String query, int itemsPerPage)
        throws PnetDataClientException {
        return restrict(SCROLL_KEY, true).execute(language, query, 0, itemsPerPage);
    }
}
