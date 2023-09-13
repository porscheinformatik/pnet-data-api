package pnet.data.api.contracttype;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictContractState;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * @author cet
 */
public class ContractTypeDataFind extends AbstractFind<ContractTypeItemDTO, ContractTypeDataFind> implements
    RestrictMatchcode<ContractTypeDataFind>, RestrictTenant<ContractTypeDataFind>, RestrictBrand<ContractTypeDataFind>,
    RestrictContractState<ContractTypeDataFind>, RestrictUpdatedAfter<ContractTypeDataFind>
{

    public ContractTypeDataFind(FindFunction<ContractTypeItemDTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }

}
