package pnet.data.api.function;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivities;
import pnet.data.api.util.RestrictBrands;
import pnet.data.api.util.RestrictCompanyTypes;
import pnet.data.api.util.RestrictContractTypes;
import pnet.data.api.util.RestrictMatchcodes;
import pnet.data.api.util.RestrictNumberTypes;
import pnet.data.api.util.RestrictTenants;

/**
 * Find interface for {@link FunctionItemDTO}s
 *
 * @author HAM
 */
public class FunctionDataFind extends AbstractFind<FunctionItemDTO, FunctionDataFind>
    implements RestrictMatchcodes<FunctionDataFind>, RestrictTenants<FunctionDataFind>,
    RestrictBrands<FunctionDataFind>, RestrictCompanyTypes<FunctionDataFind>, RestrictContractTypes<FunctionDataFind>,
    RestrictActivities<FunctionDataFind>, RestrictNumberTypes<FunctionDataFind>
{
    public FunctionDataFind(FindFunction<FunctionItemDTO> findFunction, List<Pair<String, Object>> restrictItems)
    {
        super(findFunction, restrictItems);
    }
}
