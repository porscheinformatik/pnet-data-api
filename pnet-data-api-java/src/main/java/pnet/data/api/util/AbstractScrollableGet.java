package pnet.data.api.util;

import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;

/**
 * Abstract implementation of a find query.
 *
 * @author ham
 * @param <DTO> the type of the DTO
 * @param <SELF> the type of the restriction itself for fluent interface
 */
@SuppressWarnings("deprecation")
public abstract class AbstractScrollableGet<DTO, SELF extends AbstractScrollableGet<DTO, SELF>>
    extends AbstractGet<DTO, SELF> implements ScrollableGet<DTO>, Scrollable<SELF>
{
    protected AbstractScrollableGet(GetFunction<DTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

    @Override
    public PnetDataClientResultPage<DTO> executeAndScroll(int itemsPerPage) throws PnetDataClientException
    {
        return restrict("scroll", true).execute(0, itemsPerPage);
    }
}
