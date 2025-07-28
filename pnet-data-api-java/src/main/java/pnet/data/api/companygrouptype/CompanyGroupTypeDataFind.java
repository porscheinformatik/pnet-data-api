package pnet.data.api.companygrouptype;

import java.util.List;
import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * @author cet
 */
public class CompanyGroupTypeDataFind
    extends AbstractFind<CompanyGroupTypeItemDTO, CompanyGroupTypeDataFind>
    implements RestrictMatchcode<CompanyGroupTypeDataFind>, RestrictUpdatedAfter<CompanyGroupTypeDataFind> {

    public CompanyGroupTypeDataFind(
        FindFunction<CompanyGroupTypeItemDTO> findFunction,
        List<Pair<String, Object>> restricts
    ) {
        super(findFunction, restricts);
    }
}
