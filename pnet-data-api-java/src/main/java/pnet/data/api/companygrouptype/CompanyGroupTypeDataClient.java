package pnet.data.api.companygrouptype;

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
 * Data-API client for {@link CompanyGroupTypeDataDTO}s.
 * 
 * @author cet
 */
@Service
public class CompanyGroupTypeDataClient extends AbstractPnetDataApiClient<CompanyGroupTypeDataClient>
    implements GetByMatchcode<CompanyGroupTypeDataDTO>
{
    @Autowired
    public CompanyGroupTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<CompanyGroupTypeDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyGroupTypeDataDTO> resultPage = createRestCall() //
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companygrouptypes/details",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeDataDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public CompanyGroupTypeDataSearch search()
    {
        return new CompanyGroupTypeDataSearch(getMapper(), this::search, null);
    }

    protected PnetDataClientResultPage<CompanyGroupTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO> resultPage = createRestCall()
            .parameter("l", language)
            .parameter("q", query)
            .parameters(restricts)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companygrouptypes/search",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> search(language, query, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public CompanyGroupTypeDataFind find()
    {
        return new CompanyGroupTypeDataFind(getMapper(), this::find, null);
    }

    protected PnetDataClientResultPage<CompanyGroupTypeItemDTO> find(Locale language,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO> resultPage = createRestCall()
            .parameters(restricts)
            .parameter("l", language)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companygrouptypes/find",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO>>()
                {
                });

        resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }
}
