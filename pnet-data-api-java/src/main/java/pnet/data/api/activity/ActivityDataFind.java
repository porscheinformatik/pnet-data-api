package pnet.data.api.activity;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrands;
import pnet.data.api.util.RestrictCompanyTypes;
import pnet.data.api.util.RestrictContractTypes;
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
    RestrictUpdatedAfter<ActivityDataFind>
{

    public ActivityDataFind(ObjectMapper mapper, FindFunction<ActivityItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }

}
