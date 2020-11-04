package pnet.data.api.company;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ById;
import pnet.data.api.util.ByMatchcode;
import pnet.data.api.util.CompanyMergable;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.IncludeInactive;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictCompanyNumber;
import pnet.data.api.util.RestrictDataProcessingRegisterNumber;
import pnet.data.api.util.RestrictDatedBackUntil;
import pnet.data.api.util.RestrictEmail;
import pnet.data.api.util.RestrictIban;
import pnet.data.api.util.RestrictSapNumber;
import pnet.data.api.util.RestrictTenant;
import pnet.data.api.util.RestrictVatIdNumber;

/**
 * @author cet
 */
public class CompanyDataGet extends AbstractGet<CompanyDataDTO, CompanyDataGet> implements
    RestrictTenant<CompanyDataGet>, ById<CompanyDataDTO, CompanyDataGet>, ByMatchcode<CompanyDataDTO, CompanyDataGet>,
    RestrictVatIdNumber<CompanyDataGet>, RestrictSapNumber<CompanyDataGet>, RestrictCompanyNumber<CompanyDataGet>,
    RestrictIban<CompanyDataGet>, RestrictEmail<CompanyDataGet>, RestrictDataProcessingRegisterNumber<CompanyDataGet>,
    RestrictDatedBackUntil<CompanyDataGet>, IncludeInactive<CompanyDataGet>, CompanyMergable<CompanyDataGet>
{

    public CompanyDataGet(GetFunction<CompanyDataDTO> getFunction, List<Pair<String, Object>> restricts)
    {
        super(getFunction, restricts);
    }

    public CompanyDataDTO byVatIdNumber(String vatIdNumber) throws PnetDataClientException
    {
        return allByVatIdNumbers(Arrays.asList(vatIdNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByVatIdNumbers(List<String> vatIdNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return vatIdNumbers(vatIdNumbers).execute(pageIndex, itemsPerPage);
    }

    public CompanyDataDTO bySapNumber(String sapNumber) throws PnetDataClientException
    {
        return allBySapNumbers(Arrays.asList(sapNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allBySapNumbers(List<String> sapNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return sapNumbers(sapNumbers).execute(pageIndex, itemsPerPage);
    }

    public CompanyDataDTO byCompanyNumber(String companyNumber) throws PnetDataClientException
    {
        return allByCompanyNumbers(Arrays.asList(companyNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByCompanyNumbers(List<String> companyNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return companyNumbers(companyNumbers).execute(pageIndex, itemsPerPage);
    }

    public CompanyDataDTO byIban(String iban) throws PnetDataClientException
    {
        return allByIbans(Arrays.asList(iban), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByIbans(List<String> ibans, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return ibans(ibans).execute(pageIndex, itemsPerPage);
    }

    public CompanyDataDTO byEmail(String email) throws PnetDataClientException
    {
        return allByEmails(Arrays.asList(email), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByEmails(List<String> emails, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return emails(emails).execute(pageIndex, itemsPerPage);
    }

    public CompanyDataDTO byDataProcessingRegisterNumber(String dataProcessingRegisterNumber)
        throws PnetDataClientException
    {
        return allByEmails(Arrays.asList(dataProcessingRegisterNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByDataProcessingRegisterNumbers(
        List<String> dataProcessingRegisterNumbers, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return dataProcessingRegisterNumbers(dataProcessingRegisterNumbers).execute(pageIndex, itemsPerPage);
    }
}
