package pnet.data.api.contracttype;

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
 * Data-API client for {@link ContractTypeDataDTO}s.
 *
 * @author cet
 */
@Service
public class ContractTypeDataClient extends AbstractPnetDataApiClient<ContractTypeDataClient>
{

    public ContractTypeDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public ContractTypeDataGet get()
    {
        return new ContractTypeDataGet(this::get, null);
    }

    protected PnetDataClientResultPage<ContractTypeDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/contracttypes/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> get(restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ContractTypeDataSearch search()
    {
        return new ContractTypeDataSearch(this::search, null);
    }

    protected PnetDataClientResultPage<ContractTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractTypeItemDTO> resultPage = restCall
                .parameter("l", language)
                .parameter("q", query)
                .parameters(restricts)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/contracttypes/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> search(language, query, restricts, index, itemsPerPage));

            return resultPage;
        });
    }

    public ContractTypeDataFind find()
    {
        return new ContractTypeDataFind(this::find, null);
    }

    protected PnetDataClientResultPage<ContractTypeItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .parameter("l", language)
                .parameter("p", pageIndex)
                .parameter("pp", itemsPerPage)
                .path("/api/v1/contracttypes/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(index -> find(language, restricts, index, itemsPerPage));

            return resultPage;
        });
    }
}
