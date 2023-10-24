package pnet.data.api.legalform;

import java.util.Collections;
import java.util.List;

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
        return new LegalFormDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<LegalFormDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<LegalFormDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/legalforms/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<LegalFormDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public LegalFormDataSearch search()
    {
        return new LegalFormDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<LegalFormItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<LegalFormItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/legalforms/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<LegalFormItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public LegalFormDataFind find()
    {
        return new LegalFormDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<LegalFormItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<LegalFormItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/legalforms/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<LegalFormItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
