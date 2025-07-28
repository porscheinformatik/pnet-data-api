package pnet.data.api.function;

import java.util.List;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenant;

/**
 * @author cet
 */
public class FunctionDataGet
    extends AbstractGet<FunctionDataDTO, FunctionDataGet>
    implements
        RestrictTenant<FunctionDataGet>,
        IncludeInactive<FunctionDataGet>,
        ByMatchcode<FunctionDataDTO, FunctionDataGet> {

    public FunctionDataGet(GetFunction<FunctionDataDTO> getFunction, List<Pair<String, Object>> restricts) {
        super(getFunction, restricts);
    }
}
