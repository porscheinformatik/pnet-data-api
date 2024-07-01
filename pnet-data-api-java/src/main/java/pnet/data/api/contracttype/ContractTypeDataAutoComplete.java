package pnet.data.api.contracttype;

import java.util.List;

import pnet.data.api.util.AbstractAutoComplete;
import pnet.data.api.util.AutoCompleteFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictTenant;

/**
 * AutoComplete interface for company types.
 *
 * @author ham
 */
public class ContractTypeDataAutoComplete
    extends AbstractAutoComplete<ContractTypeAutoCompleteDTO, ContractTypeDataAutoComplete>
    implements RestrictTenant<ContractTypeDataAutoComplete>, RestrictBrand<ContractTypeDataAutoComplete>
{
    public ContractTypeDataAutoComplete(AutoCompleteFunction<ContractTypeAutoCompleteDTO> autoCompleteFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(autoCompleteFunction, restrictItems);
    }
}
