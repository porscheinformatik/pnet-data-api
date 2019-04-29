package pnet.data.api.advisortype;

import static pnet.data.api.util.MockFilters.*;

import java.util.List;
import java.util.Locale;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.PnetDataApiContext;
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
public class AdvisorTypeDataClientMock extends AdvisorTypeDataClient
    implements ItemClientMock<AdvisorTypeItemDTO, AdvisorTypeDataClientMock>,
    DataClientMock<AdvisorTypeDataDTO, AdvisorTypeDataClientMock>
{

    public AdvisorTypeDataClientMock(PnetDataApiContext context)
    {
        super(context);

        MockStore<AdvisorTypeItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultDescriptionQueryFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);

        MockStore<AdvisorTypeDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<AdvisorTypeDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        List<AdvisorTypeDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPage<AdvisorTypeItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        List<AdvisorTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPage<AdvisorTypeItemDTO> search(Locale language, String query,
        List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        List<AdvisorTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }
}
