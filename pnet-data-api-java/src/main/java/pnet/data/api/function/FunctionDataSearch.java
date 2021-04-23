package pnet.data.api.function;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictApprovalNeeded;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictNumberType;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictVisibility;
import pnet.data.api.util.SearchFunction;

/**
 * Search interface for {@link FunctionItemDTO}s
 *
 * @author HAM
 */
public class FunctionDataSearch extends AbstractSearch<FunctionItemDTO, FunctionDataSearch>
    implements RestrictTenant<FunctionDataSearch>, RestrictBrand<FunctionDataSearch>,
    RestrictCompanyType<FunctionDataSearch>, RestrictContractType<FunctionDataSearch>,
    RestrictActivity<FunctionDataSearch>, RestrictNumberType<FunctionDataSearch>,
    RestrictVisibility<FunctionDataSearch>, RestrictApprovalNeeded<FunctionDataSearch>
{
    public FunctionDataSearch(SearchFunction<FunctionItemDTO> searchFunction, List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}
