package pnet.data.api.company;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.DefaultPnetDataClientResultPageWithAggregates;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregates;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link CompanyDataDTO}s.
 */
@Service
public class CompanyDataClient extends AbstractPnetDataApiClient<CompanyDataClient>
{

    @Autowired
    public CompanyDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public CompanyDataGet get()
    {
        return new CompanyDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<CompanyDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/details", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataSearch search()
    {
        return new CompanyDataSearch(this::search, null);
    }

    protected PnetDataClientResultPageWithAggregates<CompanyItemDTO, CompanyAggregatesDTO> search(Locale language,
        String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPageWithAggregates<CompanyItemDTO, CompanyAggregatesDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/search",
                    new GenericType.Of<DefaultPnetDataClientResultPageWithAggregates<CompanyItemDTO, CompanyAggregatesDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public CompanyDataFind find()
    {
        return new CompanyDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<CompanyItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/companies/find", new GenericType.Of<DefaultPnetDataClientResultPage<CompanyItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<CompanyItemDTO> next(String scrollId) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .get("/api/v1/companies/next/{scrollId}",
                    new GenericType.Of<DefaultPnetDataClientResultPage<CompanyItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }
}
