package pnet.data.api.companygrouptype;

import java.util.List;
import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * @author cet
 */
public class CompanyGroupTypeDataSearch extends AbstractSearch<CompanyGroupTypeItemDTO, CompanyGroupTypeDataSearch> {

    public CompanyGroupTypeDataSearch(
        SearchFunction<CompanyGroupTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restrictItems
    ) {
        super(searchFunction, restrictItems);
    }
}
