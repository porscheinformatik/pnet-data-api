package pnet.data.api.company;

import static pnet.data.api.util.MockFilters.*;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import pnet.data.api.GeoDistance;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregates;
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
public class CompanyDataClientMock extends CompanyDataClient implements
    ItemClientMock<CompanyItemDTO, CompanyDataClientMock>, DataClientMock<CompanyDataDTO, CompanyDataClientMock>
{

    public CompanyDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<CompanyItemDTO> itemStore = getItemStore();

        itemStore
            .addFilter("q", whenMatches(CompanyItemDTO::getCompanyId, CompanyItemDTO::getMatchcode,
                CompanyItemDTO::getName, CompanyItemDTO::getMarketingName, CompanyItemDTO::getCompanyNumber));
        itemStore.addFilter("id", whenEquals(CompanyItemDTO::getCompanyId));
        itemStore.addFilter("companyNumber", whenEquals(CompanyItemDTO::getCompanyNumber));
        itemStore
            .addFilter("b", withCollection(CompanyItemDTO::getBrands, whenEquals(CompanyBrandLinkDTO::getMatchcode)));
        itemStore.addFilter("poastCode", whenEquals(CompanyItemDTO::getPostalCode));
        itemStore.addFilter("countryCode", whenEquals(CompanyItemDTO::getCountryCode));
        itemStore
            .addFilter("type", withCollection(CompanyItemDTO::getTypes, whenEquals(CompanyTypeLinkDTO::getMatchcode)));
        itemStore.<GeoDistance> addFilter("location", (container, value) -> value.contains(container.getLocation()));

        addDefaultMatchcodeFilter(itemStore);
        addDefaultTenantsFilter(itemStore);
        addDefaultLastUpdateFilter(itemStore);
        addDefaultScrollDummy(itemStore);

        MockStore<CompanyDataDTO> dataStore = getDataStore();

        dataStore.addFilter("id", whenEquals(CompanyDataDTO::getCompanyId));
        dataStore.addFilter("vatIdNumber", whenEquals(CompanyDataDTO::getVatIdNumber));
        dataStore.addFilter("sapNumber", whenEquals(CompanyDataDTO::getSapNumber));
        dataStore.addFilter("companyNumber", whenEquals(CompanyDataDTO::getCompanyNumber));
        dataStore.addFilter("iban", whenEquals(CompanyDataDTO::getIban));
        dataStore.addFilter("email", whenEquals(CompanyDataDTO::getEmail));
        dataStore
            .addFilter("dataProcessingRegisterNumber", whenEquals(CompanyDataDTO::getDataProcessingRegisterNumber));
        dataStore.addFilter("commercialRegisterNumber", whenEquals(CompanyDataDTO::getCommercialRegisterNumber));
        dataStore.addFilter("iban", whenEquals(CompanyDataDTO::getIban));

        addDefaultMatchcodeFilter(dataStore);
        addDefaultTenantsFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<CompanyDataDTO> get(List<Pair<String, Object>> restricts, int pageIndex,
        int itemsPerPage) throws PnetDataClientException
    {
        List<CompanyDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPage<CompanyItemDTO> find(Locale language, List<Pair<String, Object>> restricts,
        int pageIndex, int itemsPerPage) throws PnetDataClientException
    {
        List<CompanyItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(entries, pageIndex, itemsPerPage);
    }

    @Override
    protected PnetDataClientResultPageWithAggregates<CompanyItemDTO, CompanyAggregatesDTO> search(Locale language,
        String query, List<Pair<String, Object>> restricts, int pageIndex, int itemsPerPage)
        throws PnetDataClientException
    {
        List<CompanyItemDTO> entries = findItems(restricts);

        List<CompanyTenantAggregateDTO> aggregatedTenants =
            MockUtils.aggregateTenants(entries, CompanyTenantAggregateDTO::new);
        List<CompanyBrandAggregateDTO> aggregatedBrands = MockUtils
            .aggregateFlat(entries, entry -> entry.getBrands().stream().map(CompanyBrandLinkDTO::getMatchcode),
                CompanyBrandAggregateDTO::new);
        List<CompanyTypeAggregateDTO> aggregatedTypes = MockUtils
            .aggregateFlat(entries, entry -> entry.getTypes().stream().map(CompanyTypeLinkDTO::getMatchcode),
                CompanyTypeAggregateDTO::new);

        CompanyAggregatesDTO aggregates =
            new CompanyAggregatesDTO(aggregatedTenants, aggregatedBrands, aggregatedTypes, Collections.emptyList());

        return MockUtils.mockResultPageWithAggregates(entries, aggregates, pageIndex, itemsPerPage);
    }
}
