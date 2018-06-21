package pnet.data.api.companygrouptype;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * <<<<<<< Updated upstream Search object for {@link CompanyGroupTypeDataClient}. =======
 *
 * @author cet
 *
 *         >>>>>>> Stashed changes
 */
public class CompanyGroupTypeDataSearch extends AbstractSearch<CompanyGroupTypeItemDTO, CompanyGroupTypeDataSearch>
{
    public CompanyGroupTypeDataSearch(SearchFunction<CompanyGroupTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }
}
