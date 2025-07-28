package pnet.data.api.person;

import java.util.List;
import pnet.data.api.util.AbstractAutoComplete;
import pnet.data.api.util.AutoCompleteFunction;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictApproved;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictCredentialsAvailable;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.RestrictInternal;
import pnet.data.api.util.RestrictPersonHierarchy;
import pnet.data.api.util.RestrictPersonType;
import pnet.data.api.util.RestrictTenant;

/**
 * AutoComplete interface for persons.
 *
 * @author ham
 */
public class PersonDataAutoComplete
    extends AbstractAutoComplete<PersonAutoCompleteDTO, PersonDataAutoComplete>
    implements
        RestrictTenant<PersonDataAutoComplete>,
        RestrictPersonType<PersonDataAutoComplete>,
        RestrictCompanyId<PersonDataAutoComplete>,
        RestrictCompanyNumber<PersonDataAutoComplete>,
        RestrictCompany<PersonDataAutoComplete>,
        RestrictFunction<PersonDataAutoComplete>,
        RestrictActivity<PersonDataAutoComplete>,
        RestrictPersonHierarchy<PersonDataAutoComplete>,
        RestrictCredentialsAvailable<PersonDataAutoComplete>,
        RestrictInternal<PersonDataAutoComplete>,
        RestrictApproved<PersonDataAutoComplete>,
        IncludeInactive<PersonDataAutoComplete>,
        RestrictDatedBackUntil<PersonDataAutoComplete>,
        CompanyMergable<PersonDataAutoComplete> {

    public PersonDataAutoComplete(
        AutoCompleteFunction<PersonAutoCompleteDTO> autoCompleteFunction,
        List<Pair<String, Object>> restrictItems
    ) {
        super(autoCompleteFunction, restrictItems);
    }
}
