package pnet.data.api.externalbrand;

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
public class ExternalBrandDataClientMock extends ExternalBrandDataClient
    implements ItemClientMock<ExternalBrandItemDTO, ExternalBrandDataClientMock>,
    DataClientMock<ExternalBrandDataDTO, ExternalBrandDataClientMock>
{

    public ExternalBrandDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<ExternalBrandItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);
        addDefaultScrollDummy(itemStore);

        MockStore<ExternalBrandDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<ExternalBrandDataDTO> get(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<ExternalBrandDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ExternalBrandItemDTO> find(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<ExternalBrandItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ExternalBrandItemDTO> search(List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<ExternalBrandItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
