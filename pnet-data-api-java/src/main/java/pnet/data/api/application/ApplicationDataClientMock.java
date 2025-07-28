package pnet.data.api.application;

import static pnet.data.api.util.MockFilters.*;

import java.util.List;
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
public class ApplicationDataClientMock
    extends ApplicationDataClient
    implements
        ItemClientMock<ApplicationItemDTO, ApplicationDataClientMock>,
        DataClientMock<ApplicationDataDTO, ApplicationDataClientMock> {

    public ApplicationDataClientMock(PnetDataApiContext context) {
        super(context);
        MockStore<ApplicationItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);
        addDefaultScrollDummy(itemStore);

        MockStore<ApplicationDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<ApplicationDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<ApplicationDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ApplicationItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<ApplicationItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<ApplicationItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<ApplicationItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
