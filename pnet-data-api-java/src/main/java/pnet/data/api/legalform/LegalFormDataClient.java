package pnet.data.api.legalform;

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
 * Data-API client for {@link LegalFormDataDTO}s.
 *
 * @author cet
 */
@Service
public class LegalFormDataClient extends AbstractPnetDataApiClient<LegalFormDataClient>
{
    @Autowired
    public LegalFormDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public LegalFormDataGet get()
    {
        return new LegalFormDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<LegalFormDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<LegalFormDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/legalforms/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<LegalFormDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public LegalFormDataSearch search()
    {
        return new LegalFormDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<LegalFormItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<LegalFormItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/legalforms/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<LegalFormItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public LegalFormDataFind find()
    {
        return new LegalFormDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<LegalFormItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<LegalFormItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/legalforms/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<LegalFormItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
