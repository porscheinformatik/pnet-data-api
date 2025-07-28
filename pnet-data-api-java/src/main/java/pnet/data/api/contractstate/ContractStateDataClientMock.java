package pnet.data.api.contractstate;

import static pnet.data.api.util.MockFilters.*;

import java.util.List;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.PnetDataApiContextMock;
import pnet.data.api.util.DataClientMock;
import pnet.data.api.util.ItemClientMock;
import pnet.data.api.util.MockStore;
import pnet.data.api.util.MockUtils;
import pnet.data.api.util.Pair;

/**
 * A mock, that can be used for testing with very basic filtering.
 *
 * @author HAM
 */
public class ContractStateDataClientMock
    extends ContractStateDataClient
    implements
        ItemClientMock<ContractStateItemDTO, ContractStateDataClientMock>,
        DataClientMock<ContractStateDataDTO, ContractStateDataClientMock> {

    public ContractStateDataClientMock() {
        super(new PnetDataApiContextMock());
        MockStore<ContractStateItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);

        MockStore<ContractStateDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<ContractStateDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<ContractStateDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ContractStateItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<ContractStateItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ContractStateItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<ContractStateItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
