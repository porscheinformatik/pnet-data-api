package pnet.data.api.companytype;

import java.util.List;

import pnet.data.api.util.AbstractAutoComplete;
import pnet.data.api.util.AutoCompleteFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenant;

/**
 * AutoComplete interface for company types.
 *
 * @author ham
 */
public class CompanyTypeDataAutoComplete extends AbstractAutoComplete<CompanyTypeAutoCompleteDTO, CompanyTypeDataAutoComplete>
    implements RestrictTenant<CompanyTypeDataAutoComplete>
{
    public CompanyTypeDataAutoComplete(AutoCompleteFunction<CompanyTypeAutoCompleteDTO> autoCompleteFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(autoCompleteFunction, restrictItems);
    }
}
