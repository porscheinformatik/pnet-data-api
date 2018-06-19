package pnet.data.api.application;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * @author cet
 *
 */
public class ApplicationDataSearch extends AbstractSearch<ApplicationItemDTO, ApplicationDataSearch>
{

    protected ApplicationDataSearch(ObjectMapper mapper, SearchFunction<ApplicationItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }

}
