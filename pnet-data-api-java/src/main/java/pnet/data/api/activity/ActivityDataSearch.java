package pnet.data.api.activity;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.FilterBrands;
import pnet.data.api.util.FilterCompanyTypes;
import pnet.data.api.util.FilterTenants;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * Search for {@link CompanyTypeDataClient}.
 *
 * @author ham
 */
public class ActivityDataSearch extends AbstractSearch<ActivityItemDTO, ActivityDataSearch>
    implements FilterTenants<ActivityDataSearch>, FilterBrands<ActivityDataSearch>, FilterCompanyTypes<ActivityDataSearch>, 
{

    ActivityDataSearch(SearchFunction<ActivityItemDTO> searchFunction, List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }

}
