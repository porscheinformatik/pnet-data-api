package pnet.data.api.advisortype;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

public class AdvisorTypeDataSearch extends AbstractSearch<AdvisorTypeItemDTO, AdvisorTypeDataSearch>
{

    public AdvisorTypeDataSearch(ObjectMapper mapper, SearchFunction<AdvisorTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }

}
