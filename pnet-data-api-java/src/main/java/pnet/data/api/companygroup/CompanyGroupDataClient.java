package pnet.data.api.companygroup;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiAccessException;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.PnetDataApiServerException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;

/**
 * Data-API client for {@link CompanyGroupDataDTO}s.
 *
 * @author cet
 */
@Service
public class CompanyGroupDataClient extends AbstractPnetDataApiClient<CompanyGroupDataClient>
{

    public CompanyGroupDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> getAllByLeadingCompanyIds(List<Integer> ids, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = createRestCall() //
                .parameters("leadingCompanyId", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companygroups/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                    {
                    });

            resultPage.setNextPageSupplier(() -> getAllByLeadingCompanyIds(ids, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> getAllByCompanyIds(List<Integer> ids, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = createRestCall() //
                .parameters("companyId", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companygroups/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                    {
                    });

            resultPage.setNextPageSupplier(() -> getAllByCompanyIds(ids, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> getAllByCompanyGroupType(List<String> types, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = createRestCall() //
                .parameters("type", types)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companygroups/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                    {
                    });

            resultPage.setNextPageSupplier(() -> getAllByCompanyGroupType(types, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (ResourceAccessException e)
        {
            throw new PnetDataApiAccessException(e);
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException(e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

}
