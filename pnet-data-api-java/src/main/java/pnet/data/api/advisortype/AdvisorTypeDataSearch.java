package pnet.data.api.advisortype;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

public class AdvisorTypeDataSearch extends AbstractSearch<AdvisorTypeItemDTO, AdvisorTypeDataSearch>
{

    public AdvisorTypeDataSearch(SearchFunction<AdvisorTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }

}
