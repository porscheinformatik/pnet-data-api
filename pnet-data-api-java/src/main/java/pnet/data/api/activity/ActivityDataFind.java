package pnet.data.api.activity;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrands;
import pnet.data.api.util.RestrictCompanyTypes;
import pnet.data.api.util.RestrictContractTypes;
import pnet.data.api.util.RestrictInfoareas;
import pnet.data.api.util.RestrictMatchcodes;
import pnet.data.api.util.RestrictTenants;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find for {@link ActivityDataClient}.
 *
 * @author ham
 */
public class ActivityDataFind extends AbstractFind<ActivityItemDTO, ActivityDataFind>
    implements RestrictMatchcodes<ActivityDataFind>, RestrictTenants<ActivityDataFind>,
    RestrictBrands<ActivityDataFind>, RestrictCompanyTypes<ActivityDataFind>, RestrictContractTypes<ActivityDataFind>,
    RestrictInfoareas<ActivityDataFind>, RestrictUpdatedAfter<ActivityDataFind>
{

    ActivityDataFind(FindFunction<ActivityItemDTO> findFunction, List<Pair<String, Object>> restrictItems)
    {
        super(findFunction, restrictItems);
    }

}
