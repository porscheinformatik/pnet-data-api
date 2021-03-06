package pnet.data.api.application;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link ApplicationDataDTO}s.
 *
 * @author cet
 */
@Service
public class ApplicationDataClient extends AbstractPnetDataApiClient<ApplicationDataClient>
{

    public ApplicationDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public ApplicationDataGet get()
    {
        return new ApplicationDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<ApplicationDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ApplicationDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/applications/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationDataDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ApplicationDataSearch search()
    {
        return new ApplicationDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<ApplicationItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ApplicationItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/applications/search",
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ApplicationDataFind find()
    {
        return new ApplicationDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<ApplicationItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ApplicationItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/applications/find",
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<ApplicationItemDTO> next(String scrollId) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ApplicationItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .get("/api/v1/applications/next/{scrollId}",
                    new GenericType.Of<DefaultPnetDataClientResultPage<ApplicationItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }
}
