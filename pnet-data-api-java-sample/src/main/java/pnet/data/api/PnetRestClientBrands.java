package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pnet.data.api.brand.BrandDataClient;
import pnet.data.api.brand.BrandDataDTO;
import pnet.data.api.brand.BrandDataFind;
import pnet.data.api.brand.BrandDataGet;
import pnet.data.api.brand.BrandDataSearch;
import pnet.data.api.brand.BrandItemDTO;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictBrand;
import pnet.data.api.util.Table;

public final class PnetRestClientBrands implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final BrandDataClient client;

    PnetRestClientBrands(PnetRestClient pnetRestClient, BrandDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<String> restrictedBrands = new ArrayList<>();

    @CLI.Command(
        name = { "get brand by mc", "get brands by mc" },
        format = "<MC...>",
        description = "Returns the brands with the specified matchcodes."
    )
    public void getBrands(String... matchcodes) throws PnetDataClientException {
        BrandDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<BrandDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all brands", description = "Export all brands updated since yesterday.")
    public void exportAllBrands() throws PnetDataClientException {
        BrandDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated brands",
        format = "[updatedAfter]",
        description = "Export all brands updated since yesterday."
    )
    public void exportAllUpdatedBrands(String updatedAfter) throws PnetDataClientException {
        BrandDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find brands by mc", "find brand by mc" },
        format = "<MC...>",
        description = "Find brands by matchcodes."
    )
    public void findBrands(String... matchcodes) throws PnetDataClientException {
        BrandDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(name = { "search brands", "search brand" }, format = "<QUERY>", description = "Query brands.")
    public void searchBrands(String... qs) throws PnetDataClientException {
        BrandDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<BrandItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, BrandItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getTenants(), dto.getLastUpdate(), dto.getScore());
    }

    @CLI.Command(
        name = { "restrict brands", "restrict brand" },
        format = "[<BRAND>...]",
        description = "Places a restriction with brands for subsequent operations."
    )
    public void restrictBrands(String... brands) {
        if (brands != null && brands.length > 0) {
            restrictedBrands.addAll(Arrays.asList(brands));
        }

        pnetRestClient.getCli().info("Requests are restricted to brands: %s", restrictedBrands);
    }

    @CLI.Command(
        name = { "clear brand restrictions", "clear brand restriction" },
        description = "Removes all restrictions for brands."
    )
    public void clearBrandRestrictions() {
        pnetRestClient.getCli().info("Removed %s brand restrictions.", restrictedBrands.size());

        clearRestrictions();
    }

    @Override
    public void clearRestrictions() {
        restrictedBrands.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (request instanceof RestrictBrand && !restrictedBrands.isEmpty()) {
            pnetRestClient.getCli().info("A restriction for brands is in place: %s", restrictedBrands);
            request = ((RestrictBrand<T>) request).brands(restrictedBrands);
        }
        return request;
    }
}
