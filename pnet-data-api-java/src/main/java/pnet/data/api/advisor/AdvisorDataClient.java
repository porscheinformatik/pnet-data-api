package pnet.data.api.advisor;

import java.util.List;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
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
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorDataDTO> resultPage = restCall //
                .parameters("companyId", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisors/details", new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByCompanyId(ids, index, itemsPerPage));

            return resultPage;
        });
    }

    public PnetDataClientResultPage<AdvisorDataDTO> getAllByPersonId(List<Integer> ids, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorDataDTO> resultPage = restCall //
                .parameters("personId", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisors/details", new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByPersonId(ids, index, itemsPerPage));

            return resultPage;
        });
    }

    public PnetDataClientResultPage<AdvisorDataDTO> getAllByAdvisorType(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorDataDTO> resultPage = restCall //
                .parameters("advisorType", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisors/details", new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByAdvisorType(matchcodes, index, itemsPerPage));

            return resultPage;
        });
    }

    public PnetDataClientResultPage<AdvisorDataDTO> getAllByAdvisorDivision(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorDataDTO> resultPage = restCall //
                .parameters("advisorDivision", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisors/details", new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorDataDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> getAllByAdvisorDivision(matchcodes, index, itemsPerPage));

            return resultPage;
        });
    }
}
