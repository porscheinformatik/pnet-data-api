package pnet.data.api.function;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictBrand;
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
    implements RestrictTenants<FunctionDataSearch>, RestrictBrand<FunctionDataSearch>,
    RestrictCompanyTypes<FunctionDataSearch>, RestrictContractTypes<FunctionDataSearch>,
    RestrictActivity<FunctionDataSearch>, RestrictNumberTypes<FunctionDataSearch>
{
    public FunctionDataSearch(ObjectMapper mapper, SearchFunction<FunctionItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }
}
