package pnet.data.api.advisor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.PnetDataApiServerException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;

/**
 * Data-API client for {@link AdvisorDataDTO}s.
 */
@Service
public class AdvisorDataClient extends AbstractPnetDataApiClient<AdvisorDataClient>
{

    public AdvisorDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public PnetDataClientResultPage<AdvisorDataDTO> getAllByCompanyId(List<Integer> ids, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<AdvisorDataDTO> resultPage = createRestCall() //
                .parameters("companyId", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisors/details", new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorDataDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> getAllByCompanyId(ids, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException("Request failed", e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public PnetDataClientResultPage<AdvisorDataDTO> getAllByPersonId(List<Integer> ids, int pageIndex, int itemsPerPage)
        throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<AdvisorDataDTO> resultPage = createRestCall() //
                .parameters("personId", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisors/details", new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorDataDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> getAllByPersonId(ids, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException("Request failed", e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public PnetDataClientResultPage<AdvisorDataDTO> getAllByAdvisorType(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<AdvisorDataDTO> resultPage = createRestCall() //
                .parameters("advisorType", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisors/details", new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorDataDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> getAllByAdvisorType(matchcodes, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException("Request failed", e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public PnetDataClientResultPage<AdvisorDataDTO> getAllByAdvisorDivision(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<AdvisorDataDTO> resultPage = createRestCall() //
                .parameters("advisorDivision", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisors/details", new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorDataDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> getAllByAdvisorDivision(matchcodes, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException("Request failed", e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }
}
