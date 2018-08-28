package pnet.data.api.brand;

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
 * Client of {@link BrandDataDTO}s and {@link BrandItemDTO}s
 *
 * @author ham
 */
@Service
public class BrandDataClient extends AbstractPnetDataApiClient<BrandDataClient>
{

    @Autowired
    public BrandDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public BrandDataGet get()
    {
        return new BrandDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<BrandDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<BrandDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/brands/details", new GenericType.Of<DefaultPnetDataClientResultPage<BrandDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public BrandDataSearch search()
    {
        return new BrandDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<BrandItemDTO>

        search(Locale language, String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
            throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<BrandItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/brands/search", new GenericType.Of<DefaultPnetDataClientResultPage<BrandItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public BrandDataFind find()
    {
        return new BrandDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<BrandItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<BrandItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/brands/find", new GenericType.Of<DefaultPnetDataClientResultPage<BrandItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
