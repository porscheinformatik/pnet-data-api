package pnet.data.api.function;

import java.util.List;

import pnet.data.api.util.AbstractAutoComplete;
import pnet.data.api.util.AutoCompleteFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictApprovalNeeded;
import pnet.data.api.util.RestrictAssignableToPrivileged;
import pnet.data.api.util.RestrictAssignableToRegular;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictNumberType;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictVisibility;

/**
 * AutoComplete interface for functions.
 *
 * @author ham
 */
public class FunctionDataAutoComplete extends AbstractAutoComplete<FunctionAutoCompleteDTO, FunctionDataAutoComplete>
    implements RestrictTenant<FunctionDataAutoComplete>, RestrictBrand<FunctionDataAutoComplete>,
    RestrictCompanyType<FunctionDataAutoComplete>, RestrictContractType<FunctionDataAutoComplete>,
    RestrictActivity<FunctionDataAutoComplete>, RestrictNumberType<FunctionDataAutoComplete>,
    RestrictVisibility<FunctionDataAutoComplete>, RestrictAssignableToRegular<FunctionDataAutoComplete>,
    RestrictAssignableToPrivileged<FunctionDataAutoComplete>, RestrictApprovalNeeded<FunctionDataAutoComplete>,
    IncludeInactive<FunctionDataAutoComplete>
{
    public FunctionDataAutoComplete(AutoCompleteFunction<FunctionAutoCompleteDTO> autoCompleteFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(autoCompleteFunction, restrictItems);
    }
}
