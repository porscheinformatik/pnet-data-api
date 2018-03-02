package pnet.data.api.activity;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrands;
import pnet.data.api.util.RestrictCompanyTypes;
import pnet.data.api.util.RestrictContractTypes;
import pnet.data.api.util.RestrictInfoareas;
import pnet.data.api.util.RestrictTenants;
import pnet.data.api.util.SearchFunction;

/**
 * Search for {@link ActivityDataClient}.
 *
 * @author ham
 */
public class ActivityDataSearch extends AbstractSearch<ActivityItemDTO, ActivityDataSearch> implements
    RestrictTenants<ActivityDataSearch>, RestrictBrands<ActivityDataSearch>, RestrictCompanyTypes<ActivityDataSearch>,
    RestrictContractTypes<ActivityDataSearch>, RestrictInfoareas<ActivityDataSearch>
{

    ActivityDataSearch(SearchFunction<ActivityItemDTO> searchFunction, List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }

}
