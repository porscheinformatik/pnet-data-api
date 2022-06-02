package pnet.data.api.brand;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenant;

/**
 * @author cet
 */
public class BrandDataGet extends AbstractGet<BrandDataDTO, BrandDataGet>
    implements RestrictTenant<BrandDataGet>, ByMatchcode<BrandDataDTO, BrandDataGet>
{

    public BrandDataGet(GetFunction<BrandDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}