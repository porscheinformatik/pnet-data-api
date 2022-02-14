package pnet.data.api.companygrouptype;

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
 * Data-API client for {@link CompanyGroupTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class CompanyGroupTypeDataClient extends AbstractPnetDataApiClient<CompanyGroupTypeDataClient>
{
    @Autowired
    public CompanyGroupTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public CompanyGroupTypeDataGet get()
    {
        return new CompanyGroupTypeDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<CompanyGroupTypeDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/companygrouptypes/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyGroupTypeDataSearch search()
    {
        return new CompanyGroupTypeDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<CompanyGroupTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/companygrouptypes/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyGroupTypeDataFind find()
    {
        return new CompanyGroupTypeDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<CompanyGroupTypeItemDTO> find(Locale language,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/companygrouptypes/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyGroupTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
