package pnet.data.api.externalbrand;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

public class ExternalBrandDataSearch extends AbstractSearch<ExternalBrandItemDTO, ExternalBrandDataSearch>
{
    public ExternalBrandDataSearch(ObjectMapper mapper, SearchFunction<ExternalBrandItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }
}
