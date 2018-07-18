package pnet.data.api.companygroup;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;

/**
 * @author cet
 *
 */
public class CompanyGroupDataGet extends AbstractGet<CompanyGroupDataDTO, CompanyGroupDataGet>
{

    public CompanyGroupDataGet(GetFunction<CompanyGroupDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

    public CompanyGroupDataDTO byLeadingCompanyId(Integer leadingCompanyId) throws PnetDataClientException
    {
        return allByLeadingCompanyIds(Arrays.asList(leadingCompanyId), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByLeadingCompanyIds(List<Integer> leadingCompanyIds,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return execute("leadingCompanyId", leadingCompanyIds, pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byCompanyId(Integer companyId) throws PnetDataClientException
    {
        return allByCompanyIds(Arrays.asList(companyId), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByCompanyIds(List<Integer> companyId, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return execute("companyId", companyId, pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byCompanyGroupType(String companyGroupType) throws PnetDataClientException
    {
        return allByCompanyGroupTypes(Arrays.asList(companyGroupType), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByCompanyGroupTypes(List<String> companyGroupTypes,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return execute("type", companyGroupTypes, pageIndex, itemsPerPage);
    }

}
