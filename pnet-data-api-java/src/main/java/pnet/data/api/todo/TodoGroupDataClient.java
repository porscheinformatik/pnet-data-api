package pnet.data.api.todo;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.DefaultPnetDataClientResultPageWithAggregations;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Data API client for {@link TodoGroupItemDTO}s
 *
 * @author HAM
 */
@Service
public class TodoGroupDataClient extends AbstractPnetDataApiClient<TodoGroupDataClient>
{

    public TodoGroupDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public TodoGroupDataSearch search()
    {
        return new TodoGroupDataSearch(this::search, null);
    }

    protected PnetDataClientResultPageWithAggregations<TodoGroupItemDTO, TodoGroupAggregationsDTO> search(
        Locale language, String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPageWithAggregations<TodoGroupItemDTO, TodoGroupAggregationsDTO> resultPage =
                restCall
                    .parameter("l", language)
                    .parameter("q", query)
                    .parameters(restricts)
                    .parameter("p", pageIndex)
                    .parameter("pp", itemsPerPage)
                    .get("/api/v1/todogroups/search",
                        new GenericType.Of<DefaultPnetDataClientResultPageWithAggregations<TodoGroupItemDTO, TodoGroupAggregationsDTO>>()
                        {
                            // intentionally left blank
                        });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public TodoGroupDataFind find()
    {
        return new TodoGroupDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<TodoGroupItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<TodoGroupItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/todogroups/find", new GenericType.Of<DefaultPnetDataClientResultPage<TodoGroupItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<TodoGroupItemDTO> next(String scrollId) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<TodoGroupItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .get("/api/v1/todogroups/next/{scrollId}",
                    new GenericType.Of<DefaultPnetDataClientResultPage<TodoGroupItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

}
