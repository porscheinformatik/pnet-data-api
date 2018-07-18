package pnet.data.api.companynumbertype;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author cet
 *
 */
public class CompanyNumberTypeDataGet extends AbstractGet<CompanyNumberTypeDataDTO, CompanyNumberTypeDataGet>
    implements ByMatchcode<CompanyNumberTypeDataDTO, CompanyNumberTypeDataGet>
{

    public CompanyNumberTypeDataGet(GetFunction<CompanyNumberTypeDataDTO> getFunction,
        List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}
