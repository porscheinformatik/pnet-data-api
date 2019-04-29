package pnet.data.api.brand;

import static pnet.data.api.util.MockFilters.*;

import java.util.List;
import java.util.Locale;

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
public class BrandDataClientMock extends BrandDataClient
    implements ItemClientMock<BrandItemDTO, BrandDataClientMock>, DataClientMock<BrandDataDTO, BrandDataClientMock>
{

    public BrandDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<BrandItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultTenantsFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);

        MockStore<BrandDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
        addDefaultTenantsFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<BrandDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        List<BrandDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPage<BrandItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        List<BrandItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPage<BrandItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        List<BrandItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }
}
