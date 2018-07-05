package pnet.data.api.activity;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenant;

public class ActivityDataGet extends AbstractGet<ActivityDataDTO, ActivityDataGet>
    implements RestrictTenant<ActivityDataGet>, ByMatchcode<ActivityDataDTO, ActivityDataGet>
{

    public ActivityDataGet(GetFunction<ActivityDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}
