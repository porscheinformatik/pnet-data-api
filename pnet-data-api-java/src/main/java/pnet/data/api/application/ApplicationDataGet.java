package pnet.data.api.application;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;

/**
 * @author cet
 */
public class ApplicationDataGet extends AbstractGet<ApplicationDataDTO, ApplicationDataGet>
    implements IncludeInactive<ApplicationDataGet>, ByMatchcode<ApplicationDataDTO, ApplicationDataGet>
{
    public ApplicationDataGet(GetFunction<ApplicationDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }
}
