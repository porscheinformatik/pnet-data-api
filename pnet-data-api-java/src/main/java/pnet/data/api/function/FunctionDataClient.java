package pnet.data.api.function;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.PnetDataApiServerException;
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
        int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<FunctionDataDTO> resultPage = createRestCall() //
                .parameters("mc", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/functions/details", new GenericType.Of<DefaultPnetDataClientResultPage<FunctionDataDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException("Request failed", e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public FunctionDataSearch search()
    {
        return new FunctionDataSearch(getMapper(), this::search, null);
    }

    protected PnetDataClientResultPage<FunctionItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<FunctionItemDTO> resultPage = createRestCall()
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
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException("Request failed", e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }

    public FunctionDataFind find()
    {
        return new FunctionDataFind(getMapper(), this::find, null);
    }

    protected PnetDataClientResultPage<FunctionItemDTO>

        find(Locale language, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
            throws PnetDataApiException
    {
        try
        {
            DefaultPnetDataClientResultPage<FunctionItemDTO> resultPage = createRestCall()
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/functions/find", new GenericType.Of<DefaultPnetDataClientResultPage<FunctionItemDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        }
        catch (HttpServerErrorException e)
        {
            throw new PnetDataApiServerException("Request failed", e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataApiException("Unhandled exception", e);
        }
    }
}
