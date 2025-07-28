package pnet.data.api.contracttype;

import java.util.List;
import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.SearchFunction;

/**
 * @author cet
 */
public class ContractTypeDataSearch
    extends AbstractSearch<ContractTypeItemDTO, ContractTypeDataSearch>
    implements RestrictTenant<ContractTypeDataSearch>, RestrictBrand<ContractTypeDataSearch> {

    public ContractTypeDataSearch(
        SearchFunction<ContractTypeItemDTO> searchFunction,
        List<Pair<String, Object>> restricts
    ) {
        super(searchFunction, restricts);
    }
}
