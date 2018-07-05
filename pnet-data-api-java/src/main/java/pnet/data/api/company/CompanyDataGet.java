package pnet.data.api.company;

import java.util.Arrays;
import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.AbstractGet;
import pnet.data.api.util.ById;
import pnet.data.api.util.GetFunction;
import pnet.data.api.util.Pair;
import pnet.data.api.util.RestrictTenant;

public class CompanyDataGet extends AbstractGet<CompanyDataDTO, CompanyDataGet>
    implements RestrictTenant<CompanyDataGet>, ById<CompanyDataDTO, CompanyDataGet>
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
        return execute("vatIdNumber", vatIdNumbers, pageIndex, itemsPerPage);
    }

    public CompanyDataDTO bySapNumber(String sapNumber) throws PnetDataClientException
    {
        return allBySapNumbers(Arrays.asList(sapNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allBySapNumbers(List<String> sapNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return execute("sapNumber", sapNumbers, pageIndex, itemsPerPage);
    }

    public CompanyDataDTO byCompanyNumber(String companyNumber) throws PnetDataClientException
    {
        return allByCompanyNumbers(Arrays.asList(companyNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByCompanyNumbers(List<String> companyNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return execute("companyNumber", companyNumbers, pageIndex, itemsPerPage);
    }

    public CompanyDataDTO byIban(String iban) throws PnetDataClientException
    {
        return allByIbans(Arrays.asList(iban), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByIbans(List<String> ibans, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return execute("iban", ibans, pageIndex, itemsPerPage);
    }

    public CompanyDataDTO byEmail(String email) throws PnetDataClientException
    {
        return allByEmails(Arrays.asList(email), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByEmails(List<String> emails, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return execute("email", emails, pageIndex, itemsPerPage);
    }

    public CompanyDataDTO byDataProcessingRegisterNumber(String dataProcessingRegisterNumber)
        throws PnetDataClientException
    {
        return allByEmails(Arrays.asList(dataProcessingRegisterNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> allByDataProcessingRegisterNumbers(
        List<String> dataProcessingRegisterNumbers, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return execute("dataProcessingRegisterNumber", dataProcessingRegisterNumbers, pageIndex, itemsPerPage);
    }
}
