package pnet.data.api.advisortype;

import java.util.List;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcodes;
import pnet.data.api.util.RestrictUpdatedAfter;

public class AdvisorTypeDataFind extends AbstractFind<AdvisorTypeItemDTO, AdvisorTypeDataFind>
    implements RestrictMatchcodes<AdvisorTypeDataFind>, RestrictUpdatedAfter<AdvisorTypeDataFind>
{

    public AdvisorTypeDataFind(FindFunction<AdvisorTypeItemDTO> findFunction, List<Pair<String, Object>> restrictItems)
    {
        super(findFunction, restrictItems);
    }

}