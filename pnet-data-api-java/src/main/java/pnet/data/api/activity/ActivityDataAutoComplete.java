package pnet.data.api.activity;

import java.util.List;

import pnet.data.api.util.AbstractAutoComplete;
import pnet.data.api.util.AutoCompleteFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictApprovalNeeded;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictVisibility;

/**
 * AutoComplete interface for activities.
 *
 * @author ham
 */
public class ActivityDataAutoComplete extends AbstractAutoComplete<ActivityAutoCompleteDTO, ActivityDataAutoComplete>
    implements RestrictTenant<ActivityDataAutoComplete>, RestrictBrand<ActivityDataAutoComplete>,
    RestrictCompanyType<ActivityDataAutoComplete>, RestrictContractType<ActivityDataAutoComplete>,
    RestrictVisibility<ActivityDataAutoComplete>, RestrictApprovalNeeded<ActivityDataAutoComplete>,
    IncludeInactive<ActivityDataAutoComplete>
{
    public ActivityDataAutoComplete(AutoCompleteFunction<ActivityAutoCompleteDTO> autoCompleteFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(autoCompleteFunction, restrictItems);
    }
}
