package pnet.data.api.companygroup;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictCompany;
import pnet.data.api.util.RestrictCompanyId;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictLeadingCompany;
import pnet.data.api.util.RestrictLeadingCompanyId;
import pnet.data.api.util.RestrictLeadingCompanyNumber;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.Scrollable;

/**
 * @author cet
 *
 */
public class CompanyGroupDataGet extends AbstractGet<CompanyGroupDataDTO, CompanyGroupDataGet>
    implements RestrictLeadingCompanyId<CompanyGroupDataGet>, RestrictLeadingCompanyNumber<CompanyGroupDataGet>,
    RestrictLeadingCompany<CompanyGroupDataGet>, RestrictCompanyId<CompanyGroupDataGet>,
    RestrictCompanyNumber<CompanyGroupDataGet>, RestrictCompany<CompanyGroupDataGet>, RestrictType<CompanyGroupDataGet>,
    Scrollable<CompanyGroupDataGet>
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
        return leadingCompanyIds(leadingCompanyIds).execute(pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byCompanyId(Integer companyId) throws PnetDataClientException
    {
        return allByCompanyIds(Arrays.asList(companyId), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByCompanyIds(List<Integer> companyIds, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return companyIds(companyIds).execute(pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byLeadingCompanyNumber(String leadingCompanyNumber) throws PnetDataClientException
    {
        return allByLeadingCompanyNumbers(Arrays.asList(leadingCompanyNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByLeadingCompanyNumbers(List<String> leadingCompanyNumbers,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return leadingCompanyNumbers(leadingCompanyNumbers).execute(pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byCompanyNumber(String companyNumber) throws PnetDataClientException
    {
        return allByCompanyNumbers(Arrays.asList(companyNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByCompanyNumbers(List<String> companyNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return companyNumbers(companyNumbers).execute(pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byLeadingCompany(String leadingCompanyMatchcode) throws PnetDataClientException
    {
        return allByLeadingCompanies(Arrays.asList(leadingCompanyMatchcode), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByLeadingCompanies(List<String> leadingCompanyMatchcodes,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return leadingCompanies(leadingCompanyMatchcodes).execute(pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byCompany(String companyMatchcode) throws PnetDataClientException
    {
        return allByCompanies(Arrays.asList(companyMatchcode), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByCompanies(List<String> companyMatchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return companies(companyMatchcodes).execute(pageIndex, itemsPerPage);
    }

    public CompanyGroupDataDTO byCompanyGroupType(String companyGroupType) throws PnetDataClientException
    {
        return allByCompanyGroupTypes(Arrays.asList(companyGroupType), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> allByCompanyGroupTypes(List<String> companyGroupTypes,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return types(companyGroupTypes).execute(pageIndex, itemsPerPage);
    }

}
