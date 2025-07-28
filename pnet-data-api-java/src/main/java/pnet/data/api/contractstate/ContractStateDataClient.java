package pnet.data.api.contractstate;

import at.porscheinformatik.happyrest.GenericType;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class ContractStateDataClient extends AbstractPnetDataApiClient<ContractStateDataClient> {

    @Autowired
    public ContractStateDataClient(PnetDataApiContext context) {
        super(context);
    }

    public ContractStateDataGet get() {
        return new ContractStateDataGet(this::get, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ContractStateDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractStateDataDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/contractstates/details")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ContractStateDataDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::get);

            return resultPage;
        });
    }

    public ContractStateDataSearch search() {
        return new ContractStateDataSearch(this::search, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ContractStateItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractStateItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/contractstates/search")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ContractStateItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::search);

            return resultPage;
        });
    }

    public ContractStateDataFind find() {
        return new ContractStateDataFind(this::find, Collections.emptyList());
    }

    protected PnetDataClientResultPage<ContractStateItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        return invoke(restCall -> {
            DefaultPnetDataClientResultPage<ContractStateItemDTO> resultPage = restCall
                .parameters(restricts)
                .path("/api/v1/contractstates/find")
                .get(
                    new GenericType.Of<DefaultPnetDataClientResultPage<ContractStateItemDTO>>() {
                        // intentionally left blank
                    }
                );

            resultPage.setPageSupplier(restricts, this::find);

            return resultPage;
        });
    }
}
