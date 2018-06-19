package pnet.data.api.companygrouptype;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * <<<<<<< Updated upstream Find object for {@link CompanyGroupTypeDataClient} =======
 * 
 * @author cet
 *
 *         >>>>>>> Stashed changes
 */
public class CompanyGroupTypeDataFind extends AbstractFind<CompanyGroupTypeItemDTO, CompanyGroupTypeDataFind>
    implements RestrictMatchcode<CompanyGroupTypeDataFind>, RestrictUpdatedAfter<CompanyGroupTypeDataFind>
{

    public CompanyGroupTypeDataFind(ObjectMapper mapper, FindFunction<CompanyGroupTypeItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }
}
