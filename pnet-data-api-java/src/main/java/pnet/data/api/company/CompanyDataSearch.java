package pnet.data.api.company;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractSearch;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.RestrictContractTypes;
import pnet.data.api.util.RestrictCountryCode;
import pnet.data.api.util.RestrictHeadquarter;
import pnet.data.api.util.RestrictLocation;
import pnet.data.api.util.RestrictTenants;
import pnet.data.api.util.RestrictTypes;
import pnet.data.api.util.SearchFunction;

/**
 * Search interface for companies
 *
 * @author HAM
 */
public class CompanyDataSearch extends AbstractSearch<CompanyItemDTO, CompanyDataSearch>
    implements RestrictTenants<CompanyDataSearch>, RestrictBrand<CompanyDataSearch>,
    RestrictCountryCode<CompanyDataSearch>, RestrictTypes<CompanyDataSearch>, RestrictContractTypes<CompanyDataSearch>,
    RestrictLocation<CompanyDataSearch>, RestrictHeadquarter<CompanyDataSearch>
{

    public CompanyDataSearch(ObjectMapper mapper, SearchFunction<CompanyItemDTO> searchFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, searchFunction, restricts);
    }
}