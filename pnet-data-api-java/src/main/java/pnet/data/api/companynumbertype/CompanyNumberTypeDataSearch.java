package pnet.data.api.companynumbertype;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * <<<<<<< Updated upstream Search interface for {@link CompanyNumberTypeDataClient}. =======
 * 
 * @author cet
 *
 *         >>>>>>> Stashed changes
 */
public class CompanyNumberTypeDataSearch extends AbstractSearch<CompanyNumberTypeItemDTO, CompanyNumberTypeDataSearch>
{
    public CompanyNumberTypeDataSearch(ObjectMapper mapper, SearchFunction<CompanyNumberTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(mapper, searchFunction, restrictItems);
    }
}
