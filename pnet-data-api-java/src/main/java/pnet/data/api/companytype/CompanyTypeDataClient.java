package pnet.data.api.companytype;

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
 * Implementation of the {@link AbstractPnetDataApiClient}.
 *
 * @author ham
 */
@Service
public class CompanyTypeDataClient extends AbstractPnetDataApiClient<CompanyTypeDataClient>
{

    @Autowired
    public CompanyTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public CompanyTypeDataGet get()
    {
        return new CompanyTypeDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<CompanyTypeDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companytypes/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeDataDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyTypeDataSearch search()
    {
        return new CompanyTypeDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<CompanyTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyTypeItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companytypes/search",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyTypeDataFind find()
    {
        return new CompanyTypeDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<CompanyTypeItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companytypes/find",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyTypeItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

}
