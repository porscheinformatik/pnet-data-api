package pnet.data.api.externalbrand;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictId;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * @author cet
 */
public class ExternalBrandDataFind extends AbstractFind<ExternalBrandItemDTO, ExternalBrandDataFind>
    implements RestrictMatchcode<ExternalBrandDataFind>, RestrictId<String, ExternalBrandDataFind>,
    RestrictUpdatedAfter<ExternalBrandDataFind>
{

    public ExternalBrandDataFind(FindFunction<ExternalBrandItemDTO> findFunction, List<Pair<String, Object>> restricts)
    {
        super(findFunction, restricts);
    }

}
