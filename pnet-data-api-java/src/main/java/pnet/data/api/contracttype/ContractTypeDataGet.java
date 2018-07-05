package pnet.data.api.contracttype;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenant;

public class ContractTypeDataGet extends AbstractGet<ContractTypeDataDTO, ContractTypeDataGet>
    implements RestrictTenant<ContractTypeDataGet>, ByMatchcode<ContractTypeDataDTO, ContractTypeDataGet>
{

    public ContractTypeDataGet(GetFunction<ContractTypeDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}
