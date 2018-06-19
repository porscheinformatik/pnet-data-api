package pnet.data.api.contractstate;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * <<<<<<< Updated upstream Find interface for the {@link ContractStateDataClient}. =======
 * 
 * @author cet
 *
 *         >>>>>>> Stashed changes
 */
public class ContractStateDataFind extends AbstractFind<ContractStateItemDTO, ContractStateDataFind>
    implements RestrictMatchcode<ContractStateDataFind>, RestrictUpdatedAfter<ContractStateDataFind>
{

    protected ContractStateDataFind(ObjectMapper mapper, FindFunction<ContractStateItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }

}
