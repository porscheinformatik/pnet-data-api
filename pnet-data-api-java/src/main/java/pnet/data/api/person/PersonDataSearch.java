package pnet.data.api.person;

import java.util.List;

import pnet.data.api.util.AbstractSearchWithAggregations;
import pnet.data.api.util.AggregateNumberPerActivity;
import pnet.data.api.util.AggregateNumberPerCompany;
import pnet.data.api.util.AggregateNumberPerFunction;
import pnet.data.api.util.AggregateNumberPerTenant;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.RestrictRole;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.SearchWithAggregationsFunction;

/**
 * Search interface for persons.
 *
 * @author ham
 */
public class PersonDataSearch
    extends AbstractSearchWithAggregations<PersonItemDTO, PersonAggregationsDTO, PersonDataSearch> implements
    RestrictTenant<PersonDataSearch>, RestrictCompanyId<PersonDataSearch>, RestrictCompanyNumber<PersonDataSearch>,
    RestrictCompany<PersonDataSearch>, RestrictBrand<PersonDataSearch>, RestrictFunction<PersonDataSearch>,
    RestrictActivity<PersonDataSearch>, RestrictRole<PersonDataSearch>, CompanyMergable<PersonDataSearch>,
    AggregateNumberPerTenant<PersonDataSearch>, AggregateNumberPerCompany<PersonDataSearch>,
    AggregateNumberPerFunction<PersonDataSearch>, AggregateNumberPerActivity<PersonDataSearch>
{

    public PersonDataSearch(SearchWithAggregationsFunction<PersonItemDTO, PersonAggregationsDTO> searchFunction,
        List<Pair<String, Object>> restrictItems)
    {
        super(searchFunction, restrictItems);
    }

}