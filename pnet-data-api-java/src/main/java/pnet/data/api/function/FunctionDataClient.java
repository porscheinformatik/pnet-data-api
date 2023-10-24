package pnet.data.api.function;

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
 * Data-API client for {@link FunctionDataDTO}s.
 */
@Service
public class FunctionDataClient extends AbstractPnetDataApiClient<FunctionDataClient>
{

    @Autowired
    public FunctionDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public FunctionDataGet get()
    {
        return new FunctionDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<FunctionDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<FunctionDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/functions/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<FunctionDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public FunctionDataSearch search()
    {
        return new FunctionDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<FunctionItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<FunctionItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/functions/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<FunctionItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public FunctionDataAutoComplete autoComplete()
    {
        return new FunctionDataAutoComplete(this::autoComplete, Collections.emptyList());
    }

    protected List<FunctionAutoCompleteDTO> autoComplete(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> restCall
            .parameters(restricts)
            .path("/api/v1/functions/autocomplete")
            .get(new GenericType.Of<List<FunctionAutoCompleteDTO>>()
            {
                // intentionally left blank
            }));
    }

    public FunctionDataFind find()
    {
        return new FunctionDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<FunctionItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<FunctionItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/functions/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<FunctionItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::find);
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<FunctionItemDTO> next(String scrollId) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<FunctionItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .path("/api/v1/functions/next/{scrollId}")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<FunctionItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }
}
