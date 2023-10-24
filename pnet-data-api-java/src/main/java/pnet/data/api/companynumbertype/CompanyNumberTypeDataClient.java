package pnet.data.api.companynumbertype;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.GenericType;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.DefaultPnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Pair;

/**
 * Data-API client for {@link CompanyNumberTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class CompanyNumberTypeDataClient extends AbstractPnetDataApiClient<CompanyNumberTypeDataClient>
{

    public CompanyNumberTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public CompanyNumberTypeDataGet get()
    {
        return new CompanyNumberTypeDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyNumberTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyNumberTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companynumbertypes/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public CompanyNumberTypeDataSearch search()
    {
        return new CompanyNumberTypeDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyNumberTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companynumbertypes/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public CompanyNumberTypeDataFind find()
    {
        return new CompanyNumberTypeDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<CompanyNumberTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/companynumbertypes/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<CompanyNumberTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
