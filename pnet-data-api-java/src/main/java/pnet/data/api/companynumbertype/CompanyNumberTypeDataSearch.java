package pnet.data.api.companynumbertype;

import java.util.List;
import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * @author cet
 */
public class CompanyNumberTypeDataSearch extends AbstractSearch<CompanyNumberTypeItemDTO, CompanyNumberTypeDataSearch> {

    public CompanyNumberTypeDataSearch(
        SearchFunction<CompanyNumberTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restrictItems
    ) {
        super(searchFunction, restrictItems);
    }
}
