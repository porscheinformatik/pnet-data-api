package pnet.data.api.application;

import java.util.List;

import pnet.data.api.util.AbstractAutoComplete;
import pnet.data.api.util.AutoCompleteFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;

/**
 * AutoComplete interface for applications.
 *
 * @author ham
 */
public class ApplicationDataAutoComplete
    extends AbstractAutoComplete<ApplicationAutoCompleteDTO, ApplicationDataAutoComplete>
    implements IncludeInactive<ApplicationDataAutoComplete>
{
    public ApplicationDataAutoComplete(AutoCompleteFunction<ApplicationAutoCompleteDTO> autoCompleteFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(autoCompleteFunction, restrictItems);
    }
}
