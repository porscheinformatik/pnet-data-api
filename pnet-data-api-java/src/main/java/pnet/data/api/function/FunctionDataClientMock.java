package pnet.data.api.function;

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
public class FunctionDataClientMock extends FunctionDataClient implements
    ItemClientMock<FunctionItemDTO, FunctionDataClientMock>, DataClientMock<FunctionDataDTO, FunctionDataClientMock>
{
    public FunctionDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<FunctionItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultDescriptionQueryFilter(itemStore);
        addDefaultTenantsFilter(itemStore);
        addDefaultBrandFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);
        addDefaultScrollDummy(itemStore);

        MockStore<FunctionDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
        addDefaultTenantsFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<FunctionDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        List<FunctionDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<FunctionItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        List<FunctionItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<FunctionItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        List<FunctionItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
