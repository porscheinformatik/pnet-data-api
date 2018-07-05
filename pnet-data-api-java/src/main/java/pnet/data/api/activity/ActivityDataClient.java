package pnet.data.api.activity;

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
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link ActivityDataDTO}s.
 *
 * @author ham
 */
@Service
public class ActivityDataClient extends AbstractPnetDataApiClient<ActivityDataClient>
{

    @Autowired
    public ActivityDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public ActivityDataGet get()
    {
        return new ActivityDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<ActivityDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ActivityDataDTO> resultPage =
                restCall.parameters(restricts).parameter("p", pageIndex).parameter("pp", itemsPerPage).get(
                    "/api/v1/activities/details", new GenericType.Of<DefaultPnetDataClientResultPage<ActivityDataDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ActivityDataSearch search()
    {
        return new ActivityDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<ActivityItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ActivityItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/activities/search", new GenericType.Of<DefaultPnetDataClientResultPage<ActivityItemDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ActivityDataFind find()
    {
        return new ActivityDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<ActivityItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ActivityItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/activities/find", new GenericType.Of<DefaultPnetDataClientResultPage<ActivityItemDTO>>()
                {
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
