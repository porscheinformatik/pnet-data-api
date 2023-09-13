package pnet.data.api.activity;

import java.util.List;

import pnet.data.api.util.AbstractScrollableFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictApprovalNeeded;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictUpdatedAfter;
import pnet.data.api.util.RestrictVisibility;
import pnet.data.api.util.Scrollable;

/**
 * Find for {@link ActivityDataClient}.
 *
 * @author ham
 */
public class ActivityDataFind extends AbstractScrollableFind<ActivityItemDTO, ActivityDataFind>
    implements RestrictMatchcode<ActivityDataFind>, RestrictTenant<ActivityDataFind>, RestrictBrand<ActivityDataFind>,
    RestrictCompanyType<ActivityDataFind>, RestrictContractType<ActivityDataFind>, RestrictVisibility<ActivityDataFind>,
    RestrictApprovalNeeded<ActivityDataFind>, RestrictUpdatedAfter<ActivityDataFind>, IncludeInactive<ActivityDataFind>,
    Scrollable<ActivityDataFind>
{

    public ActivityDataFind(FindFunction<ActivityItemDTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }

}
