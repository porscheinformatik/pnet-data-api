package pnet.data.api.companygrouptype;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

public class CompanyGroupTypeDataSearch extends AbstractSearch<CompanyGroupTypeItemDTO, CompanyGroupTypeDataSearch>
{
    public CompanyGroupTypeDataSearch(ObjectMapper mapper, SearchFunction<CompanyGroupTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(mapper, searchFunction, restrictItems);
    }
}
