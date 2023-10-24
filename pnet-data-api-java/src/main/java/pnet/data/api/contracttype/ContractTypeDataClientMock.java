package pnet.data.api.contracttype;

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
public class ContractTypeDataClientMock extends ContractTypeDataClient
    implements ItemClientMock<ContractTypeItemDTO, ContractTypeDataClientMock>,
    DataClientMock<ContractTypeDataDTO, ContractTypeDataClientMock>
{

    public ContractTypeDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<ContractTypeItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultTenantsFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);

        MockStore<ContractTypeDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
        addDefaultTenantsFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<ContractTypeDataDTO> get(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<ContractTypeDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ContractTypeItemDTO> find(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<ContractTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ContractTypeItemDTO> search(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<ContractTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
