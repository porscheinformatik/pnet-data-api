package pnet.data.api.advisortype;

import java.util.List;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author cet
 */
public class AdvisorTypeDataGet
    extends AbstractGet<AdvisorTypeDataDTO, AdvisorTypeDataGet>
    implements ByMatchcode<AdvisorTypeDataDTO, AdvisorTypeDataGet> {

    public AdvisorTypeDataGet(GetFunction<AdvisorTypeDataDTO> getFunction, List<Pair<String, Object>> restricts) {
        super(getFunction, restricts);
    }
}
