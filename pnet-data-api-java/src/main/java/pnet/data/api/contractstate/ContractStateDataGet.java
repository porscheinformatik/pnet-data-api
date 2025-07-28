package pnet.data.api.contractstate;

import java.util.List;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author cet
 */
public class ContractStateDataGet
    extends AbstractGet<ContractStateDataDTO, ContractStateDataGet>
    implements ByMatchcode<ContractStateDataDTO, ContractStateDataGet> {

    public ContractStateDataGet(GetFunction<ContractStateDataDTO> getFunction, List<Pair<String, Object>> restricts) {
        super(getFunction, restricts);
    }
}
