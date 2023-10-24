package pnet.data.api.companygroup;

import java.util.Collections;
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
        return new CompanyGroupDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyGroupDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companygroups/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::get);
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<CompanyGroupDataDTO> next(String scrollId) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupDataDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .path("/api/v1/companygroups/next/{scrollId}")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }
}
