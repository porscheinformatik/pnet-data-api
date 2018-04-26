package pnet.data.api.activity;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.GetByMatchcode;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link ActivityDataDTO}s.
 *
 * @author ham
 */
@Service
public class ActivityDataClient extends AbstractPnetDataApiClient<ActivityDataClient>
    implements GetByMatchcode<ActivityDataDTO>
{

    @Autowired
    public ActivityDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<ActivityDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ActivityDataDTO> resultPage = createRestCall() //
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/activities/details", new GenericType.Of<DefaultPnetDataClientResultPage<ActivityDataDTO>>()
            {
            });

        resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public ActivityDataSearch search()
    {
        return new ActivityDataSearch(getMapper(), this::search, null);
    }

    protected PnetDataClientResultPage<ActivityItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ActivityItemDTO> resultPage = createRestCall()
            .parameter("l", language)
            .parameter("q", query)
            .parameters(restricts)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/activities/search", new GenericType.Of<DefaultPnetDataClientResultPage<ActivityItemDTO>>()
            {
            });

        resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public ActivityDataFind find()
    {
        return new ActivityDataFind(getMapper(), this::find, null);
    }

    protected PnetDataClientResultPage<ActivityItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<ActivityItemDTO> resultPage = createRestCall()
            .parameters(restricts)
            .parameter("l", language)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/activities/find", new GenericType.Of<DefaultPnetDataClientResultPage<ActivityItemDTO>>()
            {
            });

        resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }
}
