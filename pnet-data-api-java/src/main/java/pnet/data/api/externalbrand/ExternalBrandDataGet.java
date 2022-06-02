package pnet.data.api.externalbrand;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author cet
 */
public class ExternalBrandDataGet extends AbstractGet<ExternalBrandDataDTO, ExternalBrandDataGet>
    implements ByMatchcode<ExternalBrandDataDTO, ExternalBrandDataGet>
{

    public ExternalBrandDataGet(GetFunction<ExternalBrandDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}
