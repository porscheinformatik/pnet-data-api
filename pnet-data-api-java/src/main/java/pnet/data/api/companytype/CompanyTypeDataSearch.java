package pnet.data.api.companytype;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenants;
import pnet.data.api.util.SearchFunction;

/**
 * Search for {@link CompanyTypeDataClient}.
 *
 * @author ham
 */
public class CompanyTypeDataSearch extends AbstractSearch<CompanyTypeItemDTO, CompanyTypeDataSearch>
    implements RestrictTenants<CompanyTypeDataSearch>
{

    public CompanyTypeDataSearch(SearchFunction<CompanyTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }

}
