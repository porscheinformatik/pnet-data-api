package pnet.data.api.company;

import static pnet.data.api.PnetDataConstants.*;
import static pnet.data.api.util.MockFilters.*;

import java.util.Collections;
import java.util.List;

import pnet.data.api.GeoDistance;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.client.PnetDataClientResultPageWithAggregations;
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
@SuppressWarnings("deprecation")
public class CompanyDataClientMock extends CompanyDataClient
    implements ItemClientMock<CompanyItemDTO, CompanyDataClientMock>,
    DataClientMock<CompanyDataDTO, CompanyDataClientMock>
{

    public CompanyDataClientMock()
    {
        super(new PnetDataApiContextMock());

        MockStore<CompanyItemDTO> itemStore = getItemStore();

        itemStore.addFilter(QUERY_KEY,
            whenMatches(CompanyItemDTO::getCompanyId, CompanyItemDTO::getMatchcode, CompanyItemDTO::getName,
                CompanyItemDTO::getMarketingName, CompanyItemDTO::getCompanyNumber));
        itemStore.addFilter("id", whenEquals(CompanyItemDTO::getCompanyId));
        itemStore.addFilter("companyNumber", whenEquals(CompanyItemDTO::getCompanyNumber));
        itemStore.addFilter(BRAND_KEY,
            withCollection(CompanyItemDTO::getBrands, whenEquals(CompanyBrandLinkDTO::getMatchcode)));
        itemStore.addFilter("poastCode", whenEquals(CompanyItemDTO::getPostalCode));
        itemStore.addFilter("countryCode", whenEquals(CompanyItemDTO::getCountryCode));
        itemStore.addFilter("type",
            withCollection(CompanyItemDTO::getTypes, whenEquals(CompanyTypeLinkDTO::getMatchcode)));
        itemStore.<GeoDistance>addFilter("location", (container, value) -> value.contains(container.getLocation()));

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
        dataStore.addFilter("dataProcessingRegisterNumber",
            whenEquals(CompanyDataDTO::getDataProcessingRegisterNumber));
        dataStore.addFilter("commercialRegisterNumber", whenEquals(CompanyDataDTO::getCommercialRegisterNumber));
        dataStore.addFilter("iban", whenEquals(CompanyDataDTO::getIban));

        addDefaultMatchcodeFilter(dataStore);
        addDefaultTenantsFilter(dataStore);
    }

    @Override
    protected PnetDataClientResultPage<CompanyDataDTO> get(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        List<CompanyDataDTO> entries = findDatas(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPage<CompanyItemDTO> find(List<Pair<String, Object>> restricts)
        throws PnetDataClientException
    {
        List<CompanyItemDTO> entries = findItems(restricts);

        return MockUtils.mockResultPage(restricts, entries);
    }

    @Override
    protected PnetDataClientResultPageWithAggregations<CompanyItemDTO, CompanyAggregationsDTO> search(
        List<Pair<String, Object>> restricts) throws PnetDataClientException
    {
        List<CompanyItemDTO> entries = findItems(restricts);

        List<CompanyTenantAggregationDTO> aggregatedTenants =
            MockUtils.aggregateTenants(entries, CompanyTenantAggregationDTO::new);
        List<CompanyBrandAggregationDTO> aggregatedBrands =
            MockUtils.aggregateFlat(entries, entry -> entry.getBrands().stream().map(CompanyBrandLinkDTO::getMatchcode),
                (matchcode, count) -> new CompanyBrandAggregationDTO(matchcode, matchcode, count));
        List<CompanyTypeAggregationDTO> aggregatedTypes =
            MockUtils.aggregateFlat(entries, entry -> entry.getTypes().stream(),
                (companyType, count) -> new CompanyTypeAggregationDTO(companyType.getMatchcode(),
                    companyType.getMatchcode(), count));

        CompanyAggregationsDTO aggregations =
            new CompanyAggregationsDTO(aggregatedTenants, aggregatedBrands, aggregatedTypes, Collections.emptyList());

        return MockUtils.mockResultPageWithAggregations(restricts, entries, aggregations);
    }
}
