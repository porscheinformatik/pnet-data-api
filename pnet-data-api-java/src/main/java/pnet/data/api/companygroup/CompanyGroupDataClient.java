package pnet.data.api.companygroup;

import java.util.List;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

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

    public CompanyGroupDataGet get()
    {
        return new CompanyGroupDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<CompanyGroupDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companygroups/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

}
