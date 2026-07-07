package pnet.data.api.companygroup;

import java.util.List;

import pnet.data.api.PnetDataClientException;

import pnet.data.api.PnetDataConstants;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.PnetDataApiContextMock;
import pnet.data.api.util.DataClientMock;
import pnet.data.api.util.MockFilters;
import pnet.data.api.util.MockStore;
import pnet.data.api.util.MockUtils;
import pnet.data.api.util.Pair;

/**
 * A mock, that can be used for testing with very basic filtering.
 *
 * @author HAM
 */
public class CompanyGroupDataClientMock
    extends CompanyGroupDataClient
    implements DataClientMock<CompanyGroupDataDTO, CompanyGroupDataClientMock> {

    public CompanyGroupDataClientMock() {
        super(new PnetDataApiContextMock());
        MockStore<CompanyGroupDataDTO> dataStore = getDataStore();

        dataStore.addFilter("leadingCompanyId", MockFilters.whenEquals(CompanyGroupDataDTO::getLeadingCompanyId));
        dataStore.addFilter("leadingCompanyNumber", MockFilters.whenEquals(CompanyGroupDataDTO::getLeadingCompanyNumber));
        dataStore.addFilter("leadingCompany", MockFilters.whenEquals(CompanyGroupDataDTO::getLeadingCompanyMatchcode));
        dataStore.addFilter(PnetDataConstants.TENANT_KEY, MockFilters.whenEquals(CompanyGroupDataDTO::getTenant));
        dataStore.addFilter(
            "companyId",
            MockFilters.withCollection(CompanyGroupDataDTO::getMembers, MockFilters.whenEquals(CompanyGroupMemberLinkDTO::getCompanyId))
        );
        dataStore.addFilter(
            "companyNumber",
            MockFilters.withCollection(CompanyGroupDataDTO::getMembers, MockFilters.whenEquals(CompanyGroupMemberLinkDTO::getCompanyNumber))
        );
        dataStore.addFilter(
            "company",
            MockFilters.withCollection(CompanyGroupDataDTO::getMembers, MockFilters.whenEquals(CompanyGroupMemberLinkDTO::getCompanyMatchcode))
        );

        MockFilters.addDefaultScrollDummy(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<CompanyGroupDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<CompanyGroupDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
