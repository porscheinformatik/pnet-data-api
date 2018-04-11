package pnet.data.api.function;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictBrand;
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
    RestrictBrand<FunctionDataFind>, RestrictCompanyTypes<FunctionDataFind>, RestrictContractTypes<FunctionDataFind>,
    RestrictActivity<FunctionDataFind>, RestrictNumberTypes<FunctionDataFind>
{
    public FunctionDataFind(ObjectMapper mapper, FindFunction<FunctionItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }
}
