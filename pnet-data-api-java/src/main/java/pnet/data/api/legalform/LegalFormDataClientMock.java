package pnet.data.api.legalform;

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
public class LegalFormDataClientMock
    extends LegalFormDataClient
    implements
        ItemClientMock<LegalFormItemDTO, LegalFormDataClientMock>,
        DataClientMock<LegalFormDataDTO, LegalFormDataClientMock> {

    public LegalFormDataClientMock() {
        super(new PnetDataApiContextMock());
        MockStore<LegalFormItemDTO> itemStore = getItemStore();

        addDefaultLabelQueryFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);

        MockStore<LegalFormDataDTO> dataStore = getDataStore();

        addDefaultMatchcodeFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<LegalFormDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<LegalFormDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<LegalFormItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<LegalFormItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<LegalFormItemDTO> search(List<Pair<String, Object>> restricts)
        throws PnetDataClientException {
        List<LegalFormItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }
}
