package pnet.data.api.contractstate;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.SearchFunction;

/**
 * <<<<<<< Updated upstream Search interface for {@link ContractStateDataClient}. =======
 * 
 * @author cet
 *
 *         >>>>>>> Stashed changes
 */
public class ContractStateDataSearch extends AbstractSearch<ContractStateItemDTO, ContractStateDataSearch>
{

    public ContractStateDataSearch(ObjectMapper mapper, SearchFunction<ContractStateItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }

}
