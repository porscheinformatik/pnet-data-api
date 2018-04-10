package pnet.data.api.company;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictBrands;
import pnet.data.api.util.RestrictContractTypes;
import pnet.data.api.util.RestrictCountryCodes;
import pnet.data.api.util.RestrictExternalBrands;
import pnet.data.api.util.RestrictHeadquarter;
import pnet.data.api.util.RestrictIds;
import pnet.data.api.util.RestrictLocation;
import pnet.data.api.util.RestrictPostalCodes;
import pnet.data.api.util.RestrictTenants;
import pnet.data.api.util.RestrictTypes;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find interface for companies
 *
 * @author HAM
 */
public class CompanyDataFind extends AbstractFind<CompanyItemDTO, CompanyDataFind>
    implements RestrictIds<CompanyDataFind>, RestrictTenants<CompanyDataFind>, RestrictBrands<CompanyDataFind>,
    RestrictPostalCodes<CompanyDataFind>, RestrictCountryCodes<CompanyDataFind>, RestrictTypes<CompanyDataFind>,
    RestrictContractTypes<CompanyDataFind>, RestrictLocation<CompanyDataFind>, RestrictExternalBrands<CompanyDataFind>,
    RestrictHeadquarter<CompanyDataFind>, RestrictUpdatedAfter<CompanyDataFind>
{

    public CompanyDataFind(ObjectMapper mapper, FindFunction<CompanyItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }
}