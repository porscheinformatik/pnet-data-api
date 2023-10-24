package pnet.data.api.contracttype;

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
        return new ContractTypeDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ContractTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractTypeDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/contracttypes/details")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeDataDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public ContractTypeDataSearch search()
    {
        return new ContractTypeDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ContractTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/contracttypes/search")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public ContractTypeDataFind find()
    {
        return new ContractTypeDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ContractTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractTypeItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/contracttypes/find")
                .get(new GenericType.Of<DefaultPnetDataClientResultPage<ContractTypeItemDTO>>()
                {
                    // intentionally left blank
                });

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
