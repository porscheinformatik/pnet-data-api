package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.companytype.CompanyTypeDataClient;
import pnet.data.api.companytype.CompanyTypeDataDTO;
import pnet.data.api.companytype.CompanyTypeDataFind;
import pnet.data.api.companytype.CompanyTypeDataGet;
import pnet.data.api.companytype.CompanyTypeDataSearch;
import pnet.data.api.companytype.CompanyTypeItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictCompanyType;
import pnet.data.api.util.RestrictType;
import pnet.data.api.util.Table;

public final class PnetRestClientCompanyTypes implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final CompanyTypeDataClient client;

    PnetRestClientCompanyTypes(PnetRestClient pnetRestClient, CompanyTypeDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<String> restrictedCompanyTypeMatchcodes = new ArrayList<>();

    @CLI.Command(
        name = { "get company type by mc", "get company types by mc" },
        format = "<MC...>",
        description = "Returns the company types with the specified matchcodes."
    )
    public void getCompanyTypes(String... matchcodes) throws PnetDataClientException {
        CompanyTypeDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyTypeDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all company types", description = "Exports all company types.")
    public void exportAllCompanyTypes() throws PnetDataClientException {
        CompanyTypeDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<CompanyTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated company types",
        format = "[updatedAfter]",
        description = "Exports all company types updated since yesterday."
    )
    public void exportAllUpdatedCompanyTypes(String updatedAfter) throws PnetDataClientException {
        CompanyTypeDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<CompanyTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search company types", "search company type" },
        format = "<QUERY>",
        description = "Query company types."
    )
    public void searchCompanyTypes(String... qs) throws PnetDataClientException {
        CompanyTypeDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<CompanyTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, CompanyTypeItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getTenants(), dto.getLastUpdate(), dto.getScore());
    }

    @CLI.Command(
        name = { "restrict company types", "restrict company type" },
        format = "<MC...>",
        description = "Places a restriction of company types for subsequent operations."
    )
    public void restrictCompanyTypesByMatchcode(String... matchcodes) {
        if (matchcodes != null && matchcodes.length > 0) {
            restrictedCompanyTypeMatchcodes.addAll(Arrays.asList(matchcodes));
        }

        pnetRestClient
            .getCli()
            .info("Requests are restricted to company type matchcodes: %s", restrictedCompanyTypeMatchcodes);
    }

    @Override
    public void clearRestrictions() {
        restrictedCompanyTypeMatchcodes.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (
            (request instanceof RestrictCompanyType || request instanceof RestrictType) &&
            !restrictedCompanyTypeMatchcodes.isEmpty()
        ) {
            pnetRestClient
                .getCli()
                .info("A restriction for company type matchcodes is in place: %s", restrictedCompanyTypeMatchcodes);
            if (request instanceof RestrictCompanyType) {
                request = ((RestrictCompanyType<T>) request).companyTypes(restrictedCompanyTypeMatchcodes);
            }
            if (request instanceof RestrictType) {
                request = ((RestrictType<T>) request).types(restrictedCompanyTypeMatchcodes);
            }
        }
        return request;
    }
}
