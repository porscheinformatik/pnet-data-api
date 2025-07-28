package pnet.data.api.companygrouptype;

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
public class CompanyGroupTypeDataClientMock
    extends CompanyGroupTypeDataClient
    implements
        ItemClientMock<CompanyGroupTypeItemDTO, CompanyGroupTypeDataClientMock>,
        DataClientMock<CompanyGroupTypeDataDTO, CompanyGroupTypeDataClientMock> {

    public CompanyGroupTypeDataClientMock() {
        super(new PnetDataApiContextMock());
        MockStore<CompanyGroupTypeItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);

        MockStore<CompanyGroupTypeDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<CompanyGroupTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyGroupTypeDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<CompanyGroupTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyGroupTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<CompanyGroupTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyGroupTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
