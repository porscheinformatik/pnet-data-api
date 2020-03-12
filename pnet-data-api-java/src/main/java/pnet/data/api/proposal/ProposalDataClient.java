package pnet.data.api.proposal;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Data API client for {@link ProposalItemDTO}s
 *
 * @author HAM
 */
@Service
public class ProposalDataClient extends AbstractPnetDataApiClient<ProposalDataClient>
{

    public ProposalDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public ProposalDataSearch search()
    {
        return new ProposalDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<ProposalItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ProposalItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/proposals/search", new GenericType.Of<DefaultPnetDataClientResultPage<ProposalItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ProposalDataFind find()
    {
        return new ProposalDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<ProposalItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ProposalItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .get("/api/v1/proposals/find", new GenericType.Of<DefaultPnetDataClientResultPage<ProposalItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));
            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

    protected PnetDataClientResultPage<ProposalItemDTO> next(String scrollId) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ProposalItemDTO> resultPage = restCall
                .variable("scrollId", scrollId)
                .get("/api/v1/proposals/next/{scrollId}",
                    new GenericType.Of<DefaultPnetDataClientResultPage<ProposalItemDTO>>()
                    {
                        // intentionally left blank
                    });

            resultPage.setScrollSupplier(this::next);

            return resultPage;
        });
    }

}
