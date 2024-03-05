package pnet.data.api.activity;

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
public class ActivityDataClientMock extends ActivityDataClient
    implements ItemClientMock<ActivityItemDTO, ActivityDataClientMock>,
    DataClientMock<ActivityDataDTO, ActivityDataClientMock>
{

    public ActivityDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<ActivityItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultDescriptionQueryFilter(itemStore);
        addDefaultTenantsFilter(itemStore);
        addDefaultBrandFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);
        addDefaultScrollDummy(itemStore);

        MockStore<ActivityDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
        addDefaultTenantsFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<ActivityDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        List<ActivityDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ActivityItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        List<ActivityItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ActivityItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        List<ActivityItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
