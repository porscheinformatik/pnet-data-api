package pnet.data.api.advisortype;

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
import pnet.data.api.util.GetByMatchcode;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link AdvisorTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class AdvisorTypeDataClient extends AbstractPnetDataApiClient<AdvisorTypeDataClient>
    implements GetByMatchcode<AdvisorTypeDataDTO>
{

    @Autowired
    public AdvisorTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    @Override
    public PnetDataClientResultPage<AdvisorTypeDataDTO> getAllByMatchcodes(List<String> matchcodes, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorTypeDataDTO> resultPage = restCall //
                .parameters("mc", matchcodes)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisortypes/details",
                    new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorTypeDataDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> getAllByMatchcodes(matchcodes, index, itemsPerPage));

            return resultPage;
        });
    }

    public AdvisorTypeDataSearch search()
    {
        return new AdvisorTypeDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<AdvisorTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorTypeItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisortypes/search",
                    new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorTypeItemDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public AdvisorTypeDataFind find()
    {
        return new AdvisorTypeDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<AdvisorTypeItemDTO>

        find(Locale language, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
            throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<AdvisorTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/advisortypes/find",
                    new GenericType.Of<DefaultPnetDataClientResultPage<AdvisorTypeItemDTO>>()
                    {
                    });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

}
