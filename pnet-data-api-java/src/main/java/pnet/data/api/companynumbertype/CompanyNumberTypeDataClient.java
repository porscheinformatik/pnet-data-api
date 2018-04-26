package pnet.data.api.companynumbertype;

import java.util.List;
import java.util.Locale;

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
 * Data-API client for {@link CompanyNumberTypeDataDTO}s.
 */
@Service
public class CompanyNumberTypeDataClient extends AbstractPnetDataApiClient<CompanyNumberTypeDataClient>
    implements GetByMatchcode<CompanyNumberTypeDataDTO>
{

    public CompanyNumberTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<CompanyNumberTypeDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyNumberTypeDataDTO> resultPage = createRestCall() //
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companynumbertypes/details",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeDataDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public CompanyNumberTypeDataSearch search()
    {
        return new CompanyNumberTypeDataSearch(getMapper(), this::search, null);
    }

    protected PnetDataClientResultPage<CompanyNumberTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO> resultPage = createRestCall()
            .parameter("l", language)
            .parameter("q", query)
            .parameters(restricts)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companynumbertypes/search",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public CompanyNumberTypeDataFind find()
    {
        return new CompanyNumberTypeDataFind(getMapper(), this::find, null);
    }

    protected PnetDataClientResultPage<CompanyNumberTypeItemDTO> find(Locale language,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO> resultPage = createRestCall()
            .parameters(restricts)
            .parameter("l", language)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companynumbertypes/find",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }
}
