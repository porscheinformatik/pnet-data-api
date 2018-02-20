package pnet.data.api.companytype;

import java.util.List;

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
 * Implementation of the {@link CompanyTypeDataFacade}.
 *
 * @author ham
 */
@Service
public class CompanyTypeDataClient extends AbstractPnetDataApiClient<CompanyTypeDataClient>
    implements GetByMatchcode<CompanyTypeDataDTO>
{

    public CompanyTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    protected CompanyTypeDataClient newInstance(PnetDataApiContext context)
    {
        return new CompanyTypeDataClient(context);
    }

    @Override
    public PnetDataClientResultPage<CompanyTypeDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyTypeDataDTO> resultPage = createRestCall() //
            .parameters("mc", matchcodes)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companytypes/details",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeDataDTO>>() {});

        resultPage.setNextPageSupplier(() -> getAllByMatchcodes(matchcodes, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public CompanyTypeDataSearch search()
    {
        return new CompanyTypeDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<CompanyTypeItemDTO> search(String language, String query,
        List<Pair<String, Object>> filters, int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyTypeItemDTO> resultPage = createRestCall()
            .parameter("q", query)
            .parameters(filters)
            .parameter("p", pageIndex)
            .parameter("pp", itemsPerPage)
            .get("/api/v1/companytypes/search",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeItemDTO>>() {});

        resultPage.setNextPageSupplier(() -> search(language, query, filters, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

    public CompanyTypeDataFind find()
    {
        return new CompanyTypeDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<CompanyTypeItemDTO> find(String language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataApiException
    {
        DefaultPnetDataClientResultPage<CompanyTypeItemDTO> resultPage =
            createRestCall().parameters(restricts).parameter("p", pageIndex).parameter("pp", itemsPerPage).get(
                "/api/v1/companytypes/find",
                new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeItemDTO>>() {});

        resultPage.setNextPageSupplier(() -> find(language, restricts, pageIndex + 1, itemsPerPage));

        return resultPage;
    }

}
