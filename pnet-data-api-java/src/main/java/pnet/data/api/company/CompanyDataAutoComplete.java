package pnet.data.api.company;

import java.util.List;
import pnet.data.api.util.AbstractAutoComplete;
import pnet.data.api.util.AutoCompleteFunction;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBpcmManaged;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictContractState;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictCountryCode;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictLocation;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictType;

/**
 * Auto complete interface for companies
 *
 * @author HAM
 */
public class CompanyDataAutoComplete
    extends AbstractAutoComplete<CompanyAutoCompleteDTO, CompanyDataAutoComplete>
    implements
        RestrictTenant<CompanyDataAutoComplete>,
        RestrictBrand<CompanyDataAutoComplete>,
        RestrictBpcmManaged<CompanyDataAutoComplete>,
        RestrictCountryCode<CompanyDataAutoComplete>,
        RestrictType<CompanyDataAutoComplete>,
        RestrictContractType<CompanyDataAutoComplete>,
        RestrictContractState<CompanyDataAutoComplete>,
        RestrictLocation<CompanyDataAutoComplete>,
        IncludeInactive<CompanyDataAutoComplete>,
        RestrictDatedBackUntil<CompanyDataAutoComplete>,
        CompanyMergable<CompanyDataAutoComplete> {

    public CompanyDataAutoComplete(
        AutoCompleteFunction<CompanyAutoCompleteDTO> autoCompleteFunction,
        List<Pair<String, Object>> restricts
    ) {
        super(autoCompleteFunction, restricts);
    }
}
