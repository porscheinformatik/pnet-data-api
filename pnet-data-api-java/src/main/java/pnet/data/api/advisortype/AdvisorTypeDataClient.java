package pnet.data.api.advisortype;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link AdvisorTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class AdvisorTypeDataClient extends AbstractPnetDataApiClient<AdvisorTypeDataClient>
{
    @Autowired
    public AdvisorTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public AdvisorTypeDataGet get()
    {
        return new AdvisorTypeDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<AdvisorTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/advisortypes/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorTypeDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public AdvisorTypeDataSearch search()
    {
        return new AdvisorTypeDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<AdvisorTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/advisortypes/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public AdvisorTypeDataFind find()
    {
        return new AdvisorTypeDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<AdvisorTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/advisortypes/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
