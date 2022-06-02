package pnet.data.api.companygrouptype;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author cet
 */
public class CompanyGroupTypeDataGet extends AbstractGet<CompanyGroupTypeDataDTO, CompanyGroupTypeDataGet>
    implements ByMatchcode<CompanyGroupTypeDataDTO, CompanyGroupTypeDataGet>
{

    public CompanyGroupTypeDataGet(GetFunction<CompanyGroupTypeDataDTO> getFunction,
        List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}
