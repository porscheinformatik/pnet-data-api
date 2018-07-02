package pnet.data.api.company;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.GetById;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link CompanyDataDTO}s.
 */
@Service
public class CompanyDataClient extends AbstractPnetDataApiClient<CompanyDataClient> implements GetById<CompanyDataDTO>
{

    @Autowired
    public CompanyDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<CompanyDataDTO> getAllByIds(List<Integer> ids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall //
                .parameters("id", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/details", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByIds(ids, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataDTO getByVatIdNumber(String vatIdNumber) throws PnetDataClientException
    {
        return getAllByVatIdNumbers(Arrays.asList(vatIdNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> getAllByVatIdNumbers(List<String> vatIdNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall //
                .parameters("vatIdNumber", vatIdNumbers)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/details", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByVatIdNumbers(vatIdNumbers, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataDTO getBySapNumber(String sapNumber) throws PnetDataClientException
    {
        return getAllBySapNumbers(Arrays.asList(sapNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> getAllBySapNumbers(List<String> sapNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall //
                .parameters("sapNumber", sapNumbers)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/details", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllBySapNumbers(sapNumbers, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataDTO getByCompanyNumber(String companyNumber) throws PnetDataClientException
    {
        return getAllByCompanyNumbers(Arrays.asList(companyNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> getAllByCompanyNumbers(List<String> companyNumbers, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall //
                .parameters("companyNumber", companyNumbers)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/details", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByCompanyNumbers(companyNumbers, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataDTO getByIban(String iban) throws PnetDataClientException
    {
        return getAllByIbans(Arrays.asList(iban), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> getAllByIbans(List<String> ibans, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall //
                .parameters("iban", ibans)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/details", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByIbans(ibans, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataDTO getByEmail(String email) throws PnetDataClientException
    {
        return getAllByEmails(Arrays.asList(email), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> getAllByEmails(List<String> emails, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall //
                .parameters("email", emails)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/details", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByEmails(emails, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataDTO getByDataProcessingRegisterNumber(String dataProcessingRegisterNumber)
        throws PnetDataClientException
    {
        return getAllByDataProcessingRegisterNumbers(Arrays.asList(dataProcessingRegisterNumber), 0, 1).first();
    }

    public PnetDataClientResultPage<CompanyDataDTO> getAllByDataProcessingRegisterNumbers(
        List<String> dataProcessingRegisterNumbers, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall //
                .parameters("dataProcessingRegisterNumber", dataProcessingRegisterNumbers)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/details", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByDataProcessingRegisterNumbers(dataProcessingRegisterNumbers,
                index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataSearch search()
    {
        return new CompanyDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<CompanyItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/search", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyItemDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataFind find()
    {
        return new CompanyDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<CompanyItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/find", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyItemDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
