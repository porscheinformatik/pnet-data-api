package pnet.data.api.companytype;

import java.util.List;

import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author cet
 *
 */
public class CompanyTypeDataGet extends AbstractGet<CompanyTypeDataDTO, CompanyTypeDataGet>
    implements ByMatchcode<CompanyTypeDataDTO, CompanyTypeDataGet>
{

    public CompanyTypeDataGet(GetFunction<CompanyTypeDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

}
