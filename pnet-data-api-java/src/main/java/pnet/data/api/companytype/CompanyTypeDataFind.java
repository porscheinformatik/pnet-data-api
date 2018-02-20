package pnet.data.api.companytype;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcodes;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find for {@link CompanyTypeDataClient}.
 *
 * @author ham
 */
public class CompanyTypeDataFind extends AbstractFind<CompanyTypeItemDTO, CompanyTypeDataFind>
    implements RestrictMatchcodes<CompanyTypeDataFind>, RestrictUpdatedAfter<CompanyTypeDataFind>
{

    CompanyTypeDataFind(FindFunction<CompanyTypeItemDTO> findFunction, List<Pair<String, Object>> restrictItems)
    {
        super(findFunction, restrictItems);
    }

}
