package pnet.data.api.companytype;

import java.util.List;
import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find for {@link CompanyTypeDataClient}.
 *
 * @author ham
 */
public class CompanyTypeDataFind
    extends AbstractFind<CompanyTypeItemDTO, CompanyTypeDataFind>
    implements
        RestrictMatchcode<CompanyTypeDataFind>,
        RestrictTenant<CompanyTypeDataFind>,
        RestrictUpdatedAfter<CompanyTypeDataFind> {

    public CompanyTypeDataFind(FindFunction<CompanyTypeItemDTO> findFunction, List<Pair<String, Object>> restricts) {
        super(findFunction, restricts);
    }
}
