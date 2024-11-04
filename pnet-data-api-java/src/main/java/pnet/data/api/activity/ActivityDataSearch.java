package pnet.data.api.activity;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictApprovalNeeded;
import pnet.data.api.util.RestrictAssignableToPrivileged;
import pnet.data.api.util.RestrictAssignableToRegular;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictVisibility;
import pnet.data.api.util.SearchFunction;

/**
 * Search for {@link ActivityDataClient}.
 *
 * @author ham
 */
public class ActivityDataSearch extends AbstractSearch<ActivityItemDTO, ActivityDataSearch> implements
    RestrictTenant<ActivityDataSearch>, RestrictBrand<ActivityDataSearch>, RestrictCompanyType<ActivityDataSearch>,
    RestrictContractType<ActivityDataSearch>, RestrictVisibility<ActivityDataSearch>,
    RestrictAssignableToRegular<ActivityDataSearch>, RestrictAssignableToPrivileged<ActivityDataSearch>,
    RestrictApprovalNeeded<ActivityDataSearch>, IncludeInactive<ActivityDataSearch>
{

    public ActivityDataSearch(SearchFunction<ActivityItemDTO> searchFunction, List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }

}
