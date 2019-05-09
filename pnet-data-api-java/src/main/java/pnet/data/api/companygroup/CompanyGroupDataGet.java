package pnet.data.api.companygroup;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.Scrollable;

/**
 * @author cet
 *
 */
public class CompanyGroupDataGet extends AbstractGet<CompanyGroupDataDTO, CompanyGroupDataGet>
    implements Scrollable<CompanyGroupDataGet>
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

    public CompanyGroupDataDTO byLeadingCompanyNumber(String leadingCompanyNumber) throws PnetDataClientException
    {
        return allByLeadingCompanyNumbers(Arrays.asList(leadingCompanyNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByLeadingCompanyNumbers(List<String> leadingCompanyNumbers,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return execute("leadingCompanyNumber", leadingCompanyNumbers, pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byCompanyNumber(String companyNumber) throws PnetDataClientException
    {
        return allByCompanyNumbers(Arrays.asList(companyNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByCompanyNumbers(List<String> companyNumber, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return execute("companyNumber", companyNumber, pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byLeadingCompany(String leadingCompanyMatchcode) throws PnetDataClientException
    {
        return allByLeadingCompanies(Arrays.asList(leadingCompanyMatchcode), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByLeadingCompanies(List<String> leadingCompanyMatchcodes,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return execute("leadingCompany", leadingCompanyMatchcodes, pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byCompany(String companyMatchcode) throws PnetDataClientException
    {
        return allByCompanies(Arrays.asList(companyMatchcode), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByCompanies(List<String> companyMatchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return execute("company", companyMatchcodes, pageIndex, itemsPerPage);
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
