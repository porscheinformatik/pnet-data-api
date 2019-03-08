package pnet.data.api.person;

import java.util.List;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.SearchFunction;

/**
 * Search interface for persons.
 *
 * @author ham
 */
public class PersonDataSearch extends AbstractSearch<PersonItemDTO, PersonDataSearch> implements
    RestrictTenant<PersonDataSearch>, RestrictCompanyId<PersonDataSearch>, RestrictCompanyNumber<PersonDataSearch>,
    RestrictCompany<PersonDataSearch>, RestrictFunction<PersonDataSearch>, RestrictActivity<PersonDataSearch>
{

    public PersonDataSearch(SearchFunction<PersonItemDTO> searchFunction, List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }

}