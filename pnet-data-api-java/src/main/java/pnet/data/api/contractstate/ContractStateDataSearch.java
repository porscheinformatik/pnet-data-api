package pnet.data.api.contractstate;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * Search interface for {@link ContractStateDataClient}.
 */
public class ContractStateDataSearch extends AbstractSearch<ContractStateItemDTO, ContractStateDataSearch>
{

    public ContractStateDataSearch(ObjectMapper mapper, SearchFunction<ContractStateItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }

}
