package pnet.data.api.application;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * @author cet
 */
public class ApplicationDataSearch extends AbstractSearch<ApplicationItemDTO, ApplicationDataSearch>
    implements IncludeInactive<ApplicationDataSearch>
{
    public ApplicationDataSearch(SearchFunction<ApplicationItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}
