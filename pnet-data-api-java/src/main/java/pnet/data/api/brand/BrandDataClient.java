package pnet.data.api.brand;

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
 * Client of {@link BrandDataDTO}s and {@link BrandItemDTO}s
 *
 * @author ham
 */
@Service
public class BrandDataClient extends AbstractPnetDataApiClient<BrandDataClient> implements GetByMatchcode<BrandDataDTO>
{

    @Autowired
    public BrandDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<BrandDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<BrandDataDTO> resultPage =
                restCall.parameter("mc", matchcodes).parameter("p", pageIndex).parameter("pp", itemsPerPage).get(
                    "/api/v1/brands/details", new GenericType.Of<DefaultPnetDataClientResultPage<BrandDataDTO>>()
                    {
                    });

            resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

            return resultPage;
        });
    }

    public BrandDataSearch search()
    {
        return new BrandDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<BrandItemDTO>

        search(Locale language, String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
            throws PnetDataApiException
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
                });

            resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        });
    }

    public BrandDataFind find()
    {
        return new BrandDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<BrandItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<BrandItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/brands/find", new GenericType.Of<DefaultPnetDataClientResultPage<BrandItemDTO>>()
                {
                });

            resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

            return resultPage;
        });
    }
}
