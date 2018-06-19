package pnet.data.api.advisortype;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.util.AbstractFind;
import pnet.data.api.util.FindFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictMatchcode;
import pnet.data.api.util.RestrictUpdatedAfter;

/**
 * Find class for {@link AdvisorTypeItemDTO}s
 *
 * @author cet
 */
public class AdvisorTypeDataFind extends AbstractFind<AdvisorTypeItemDTO, AdvisorTypeDataFind>
    implements RestrictMatchcode<AdvisorTypeDataFind>, RestrictUpdatedAfter<AdvisorTypeDataFind>
{

    public AdvisorTypeDataFind(ObjectMapper mapper, FindFunction<AdvisorTypeItemDTO> findFunction,
        List<Pair<String, Object>> restricts)
    {
        super(mapper, findFunction, restricts);
    }

}
