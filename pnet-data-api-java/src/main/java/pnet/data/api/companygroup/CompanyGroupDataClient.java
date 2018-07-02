package pnet.data.api.companygroup;

import java.util.List;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
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
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = restCall //
                .parameters("leadingCompanyId", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companygroups/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> getAllByLeadingCompanyIds(ids, index, itemsPerPage));

            return resultPage;
        });
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> getAllByCompanyIds(List<Integer> ids, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = restCall //
                .parameters("companyId", ids)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companygroups/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> getAllByCompanyIds(ids, index, itemsPerPage));

            return resultPage;
        });
    }

    public PnetDataClientResultPage<CompanyGroupDataDTO> getAllByCompanyGroupType(List<String> types, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = restCall //
                .parameters("type", types)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companygroups/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> getAllByCompanyGroupType(types, index, itemsPerPage));

            return resultPage;
        });
    }

}
