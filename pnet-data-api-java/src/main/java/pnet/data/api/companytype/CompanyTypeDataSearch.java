package pnet.data.api.companytype;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.SearchFunction;

/**
 * Search for {@link CompanyTypeDataClient}.
 *
 * @author ham
 */
public class CompanyTypeDataSearch extends AbstractSearch<CompanyTypeItemDTO, CompanyTypeDataSearch>
    implements RestrictTenant<CompanyTypeDataSearch>
{

    public CompanyTypeDataSearch(SearchFunction<CompanyTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }

}
