package pnet.data.api.contractstate;

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
 * Data-API client for {@link ContractStateDataDTO}s.
 *
 * @author cet
 */
@Service
public class ContractStateDataClient extends AbstractPnetDataApiClient<ContractStateDataClient>
{

    @Autowired
    public ContractStateDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public ContractStateDataGet get()
    {
        return new ContractStateDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<ContractStateDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractStateDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/contractstates/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractStateDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ContractStateDataSearch search()
    {
        return new ContractStateDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<ContractStateItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractStateItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/contractstates/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractStateItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ContractStateDataFind find()
    {
        return new ContractStateDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<ContractStateItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractStateItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/contractstates/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractStateItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
