package pnet.data.api.util;

import static pnet.data.api.PnetDataConstants.*;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a find query.
 *
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the restriction itself for fluent interface
 * @author ham
 */
@SuppressWarnings("deprecation")
public abstract class AbstractScrollableFind<DTO, SELF extends AbstractScrollableFind<DTO, SELF>>
    extends AbstractFind<DTO, SELF> implements ScrollableFind<DTO>, Scrollable<SELF>
{
    protected AbstractScrollableFind(FindFunction<DTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }

    @Override
    public PnetDataClientResultPage<DTO> executeAndScroll(Locale language, int itemsPerPage)
        throws PnetDataClientException
    {
        return restrict(SCROLL_KEY, true).execute(language, 0, itemsPerPage);
    }
}
