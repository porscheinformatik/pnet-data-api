package pnet.data.api.numbertype;

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
public class NumberTypeDataClientMock extends NumberTypeDataClient
    implements ItemClientMock<NumberTypeItemDTO, NumberTypeDataClientMock>,
    DataClientMock<NumberTypeDataDTO, NumberTypeDataClientMock>
{

    public NumberTypeDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<NumberTypeItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);
        addDefaultScrollDummy(itemStore);

        MockStore<NumberTypeDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<NumberTypeDataDTO> get(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<NumberTypeDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<NumberTypeItemDTO> find(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<NumberTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<NumberTypeItemDTO> search(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<NumberTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
