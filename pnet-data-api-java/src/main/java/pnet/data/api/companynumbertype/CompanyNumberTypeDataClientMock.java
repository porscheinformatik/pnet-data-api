package pnet.data.api.companynumbertype;

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
public class CompanyNumberTypeDataClientMock
    extends CompanyNumberTypeDataClient
    implements
        ItemClientMock<CompanyNumberTypeItemDTO, CompanyNumberTypeDataClientMock>,
        DataClientMock<CompanyNumberTypeDataDTO, CompanyNumberTypeDataClientMock> {

    public CompanyNumberTypeDataClientMock() {
        super(new PnetDataApiContextMock());
        MockStore<CompanyNumberTypeItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);

        MockStore<CompanyNumberTypeDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<CompanyNumberTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyNumberTypeDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<CompanyNumberTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyNumberTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<CompanyNumberTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyNumberTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
