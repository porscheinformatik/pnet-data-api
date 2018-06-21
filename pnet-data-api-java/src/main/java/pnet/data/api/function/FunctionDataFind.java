package pnet.data.api.function;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictNumberType;
import pnet.data.api.util.RestrictTenant;

/**
 * Find interface for {@link FunctionItemDTO}s
 *
 * @author HAM
 */
public class FunctionDataFind extends AbstractFind<FunctionItemDTO, FunctionDataFind>
    implements RestrictMatchcode<FunctionDataFind>, RestrictTenant<FunctionDataFind>, RestrictBrand<FunctionDataFind>,
    RestrictCompanyType<FunctionDataFind>, RestrictContractType<FunctionDataFind>, RestrictActivity<FunctionDataFind>,
    RestrictNumberType<FunctionDataFind>
{
    public FunctionDataFind(FindFunction<FunctionItemDTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }
}
