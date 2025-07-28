package pnet.data.api.contractstate;

import java.util.List;
import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * @author cet
 */
public class ContractStateDataSearch extends AbstractSearch<ContractStateItemDTO, ContractStateDataSearch> {

    public ContractStateDataSearch(
        SearchFunction<ContractStateItemDTO> searchFunction,
        List<Pair<String, Object>> restricts
    ) {
        super(searchFunction, restricts);
    }
}
