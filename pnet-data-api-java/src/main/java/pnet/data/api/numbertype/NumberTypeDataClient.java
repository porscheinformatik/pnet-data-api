package pnet.data.api.numbertype;

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
 * Client for {@link NumberTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class NumberTypeDataClient extends AbstractPnetDataApiClient<NumberTypeDataClient>
{

    @Autowired
    public NumberTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public NumberTypeDataGet get()
    {
        return new NumberTypeDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<NumberTypeDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<NumberTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/numbertypes/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public NumberTypeDataSearch search()
    {
        return new NumberTypeDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<NumberTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<NumberTypeItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/numbertypes/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public NumberTypeDataFind find()
    {
        return new NumberTypeDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<NumberTypeItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<NumberTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/numbertypes/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<NumberTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
