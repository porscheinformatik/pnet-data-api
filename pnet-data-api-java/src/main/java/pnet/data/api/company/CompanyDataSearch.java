package pnet.data.api.company;

import java.util.List;

import pnet.data.api.util.AbstractSearchWithAggregations;
import pnet.data.api.util.AggregateNumberPerBrand;
import pnet.data.api.util.AggregateNumberPerContractType;
import pnet.data.api.util.AggregateNumberPerTenant;
import pnet.data.api.util.AggregateNumberPerType;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictContractState;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.RestrictCountryCode;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictLocation;
import pnet.data.api.util.RestrictQueryField;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.SearchWithAggregationsFunction;

/**
 * Search interface for companies
 *
 * @author HAM
 */
public class CompanyDataSearch
    extends AbstractSearchWithAggregations<CompanyItemDTO, CompanyAggregationsDTO, CompanyDataSearch>
    implements RestrictTenant<CompanyDataSearch>, RestrictBrand<CompanyDataSearch>,
    RestrictCompanyId<CompanyDataSearch>, RestrictCountryCode<CompanyDataSearch>, RestrictType<CompanyDataSearch>,
    RestrictContractType<CompanyDataSearch>, RestrictContractState<CompanyDataSearch>,
    RestrictLocation<CompanyDataSearch>, RestrictDatedBackUntil<CompanyDataSearch>, IncludeInactive<CompanyDataSearch>,
    CompanyMergable<CompanyDataSearch>, AggregateNumberPerTenant<CompanyDataSearch>,
    AggregateNumberPerBrand<CompanyDataSearch>, AggregateNumberPerType<CompanyDataSearch>,
    AggregateNumberPerContractType<CompanyDataSearch>, RestrictQueryField<CompanyDataSearch>
{

    public CompanyDataSearch(SearchWithAggregationsFunction<CompanyItemDTO, CompanyAggregationsDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(searchFunction, restricts);
    }
}