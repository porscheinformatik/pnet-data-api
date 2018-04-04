package pnet.data.api.company;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrands;
import pnet.data.api.util.RestrictCompanyIds;
import pnet.data.api.util.RestrictTenants;
import pnet.data.api.util.SearchFunction;

/**
 * Search interface for companies
 *
 * @author HAM
 */
public class CompanyDataSearch extends AbstractSearch<CompanyItemDTO, CompanyDataSearch> implements
    RestrictTenants<CompanyDataSearch>, RestrictCompanyIds<CompanyDataSearch>, RestrictBrands<CompanyDataSearch>
{

    public CompanyDataSearch(SearchFunction<CompanyItemDTO> searchFunction, List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }
}