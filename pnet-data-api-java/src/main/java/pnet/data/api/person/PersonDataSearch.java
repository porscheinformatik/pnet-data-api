package pnet.data.api.person;

import java.util.List;

import pnet.data.api.util.AbstractSearchWithAggregates;
import pnet.data.api.util.AggregateNumberPerActivity;
import pnet.data.api.util.AggregateNumberPerCompany;
import pnet.data.api.util.AggregateNumberPerFunction;
import pnet.data.api.util.AggregateNumberPerTenant;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.SearchWithAggregatesFunction;

/**
 * Search interface for persons.
 *
 * @author ham
 */
public class PersonDataSearch extends AbstractSearchWithAggregates<PersonItemDTO, PersonAggregatesDTO, PersonDataSearch>
    implements RestrictTenant<PersonDataSearch>, RestrictCompanyId<PersonDataSearch>,
    RestrictCompanyNumber<PersonDataSearch>, RestrictCompany<PersonDataSearch>, RestrictFunction<PersonDataSearch>,
    RestrictActivity<PersonDataSearch>, AggregateNumberPerTenant<PersonDataSearch>,
    AggregateNumberPerCompany<PersonDataSearch>, AggregateNumberPerFunction<PersonDataSearch>,
    AggregateNumberPerActivity<PersonDataSearch>
{

    public PersonDataSearch(SearchWithAggregatesFunction<PersonItemDTO, PersonAggregatesDTO> searchFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }

}