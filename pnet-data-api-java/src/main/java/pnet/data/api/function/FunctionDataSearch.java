package pnet.data.api.function;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivities;
import pnet.data.api.util.RestrictBrands;
import pnet.data.api.util.RestrictCompanyTypes;
import pnet.data.api.util.RestrictContractTypes;
import pnet.data.api.util.RestrictNumberTypes;
import pnet.data.api.util.RestrictTenants;
import pnet.data.api.util.SearchFunction;

/**
 * Search interface for {@link FunctionItemDTO}s
 *
 * @author HAM
 */
public class FunctionDataSearch extends AbstractSearch<FunctionItemDTO, FunctionDataSearch>
    implements RestrictTenants<FunctionDataSearch>, RestrictBrands<FunctionDataSearch>,
    RestrictCompanyTypes<FunctionDataSearch>, RestrictContractTypes<FunctionDataSearch>,
    RestrictActivities<FunctionDataSearch>, RestrictNumberTypes<FunctionDataSearch>
{
    public FunctionDataSearch(SearchFunction<FunctionItemDTO> searchFunction, List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }
}
