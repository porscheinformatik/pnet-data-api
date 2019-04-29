package pnet.data.api.companygroup;

import static pnet.data.api.util.MockFilters.*;

import java.util.List;

import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.context.PnetDataApiContextMock;
import pnet.data.api.util.DataClientMock;
import pnet.data.api.util.MockStore;
import pnet.data.api.util.MockUtils;
import pnet.data.api.util.Pair;

/**
 * A mock, that can be used for testing with very basic filtering.
 *
 * @author HAM
 */
public class CompanyGroupDataClientMock extends CompanyGroupDataClient
    implements DataClientMock<CompanyGroupDataDTO, CompanyGroupDataClientMock>
{

    public CompanyGroupDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<CompanyGroupDataDTO> dataStore = getDataStore();

        dataStore.addFilter("leadingCompanyId", whenEquals(CompanyGroupDataDTO::getLeadingCompanyId));
        dataStore.addFilter("leadingCompanyNumber", whenEquals(CompanyGroupDataDTO::getLeadingCompanyNumber));
        dataStore.addFilter("leadingCompany", whenEquals(CompanyGroupDataDTO::getLeadingCompanyMatchcode));
        dataStore
            .addFilter("companyId",
                withCollection(CompanyGroupDataDTO::getMembers, whenEquals(CompanyGroupMemberLinkDTO::getCompanyId)));
        dataStore
            .addFilter("companyNumber", withCollection(CompanyGroupDataDTO::getMembers,
                whenEquals(CompanyGroupMemberLinkDTO::getCompanyNumber)));
        dataStore
            .addFilter("company", withCollection(CompanyGroupDataDTO::getMembers,
                whenEquals(CompanyGroupMemberLinkDTO::getCompanyMatchcode)));

        addDefaultScrollDummy(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<CompanyGroupDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        List<CompanyGroupDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }
}
