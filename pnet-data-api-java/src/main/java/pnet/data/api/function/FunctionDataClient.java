package pnet.data.api.function;

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
import pnet.data.api.util.GetByMatchcode;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link FunctionDataDTO}s.
 */
@Service
public class FunctionDataClient extends AbstractPnetDataApiClient<FunctionDataClient>
    implements GetByMatchcode<FunctionDataDTO>
{

    @Autowired
    public FunctionDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<FunctionDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<FunctionDataDTO> resultPage = restCall //
                .parameters("mc", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/functions/details", new GenericType.Of<DefaultPnetDataClientResultPage<FunctionDataDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

            return resultPage;
        });
    }

    public FunctionDataSearch search()
    {
        return new FunctionDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<FunctionItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<FunctionItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/functions/search", new GenericType.Of<DefaultPnetDataClientResultPage<FunctionItemDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        });
    }

    public FunctionDataFind find()
    {
        return new FunctionDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<FunctionItemDTO>

        find(Locale language, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
            throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<FunctionItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/functions/find", new GenericType.Of<DefaultPnetDataClientResultPage<FunctionItemDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        });
    }
}
