package pnet.data.api.companytype;

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
public class CompanyTypeDataClientMock
    extends CompanyTypeDataClient
    implements
        ItemClientMock<CompanyTypeItemDTO, CompanyTypeDataClientMock>,
        DataClientMock<CompanyTypeDataDTO, CompanyTypeDataClientMock> {

    public CompanyTypeDataClientMock() {
        super(new PnetDataApiContextMock());
        MockStore<CompanyTypeItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultTenantsFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);

        MockStore<CompanyTypeDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
        addDefaultTenantsFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<CompanyTypeDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyTypeDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<CompanyTypeItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<CompanyTypeItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyTypeItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
