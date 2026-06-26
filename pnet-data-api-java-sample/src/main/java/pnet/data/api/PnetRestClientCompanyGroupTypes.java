package pnet.data.api;

import java.util.Arrays;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.companygroup.CompanyGroupDataClient;
import pnet.data.api.companygroup.CompanyGroupDataDTO;
import pnet.data.api.companygroup.CompanyGroupDataGet;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataClient;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataDTO;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataFind;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataGet;
import pnet.data.api.companygrouptype.CompanyGroupTypeDataSearch;
import pnet.data.api.companygrouptype.CompanyGroupTypeItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Table;

public final class PnetRestClientCompanyGroupTypes implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final CompanyGroupTypeDataClient client;
    private final CompanyGroupDataClient companyGroupDataClient;

    PnetRestClientCompanyGroupTypes(
        PnetRestClient pnetRestClient,
        CompanyGroupTypeDataClient client,
        CompanyGroupDataClient companyGroupDataClient
    ) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
        this.companyGroupDataClient = companyGroupDataClient;
    }

    @CLI.Command(
        name = { "get company group type by mc", "get company group types by mc" },
        format = "<MC...>",
        description = "Returns the company group types with the specified matchcodes."
    )
    public void getCompanyGroupTypes(String... matchcodes) throws PnetDataClientException {
        CompanyGroupTypeDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyGroupTypeDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(
        name = { "get company group by type", "get company groups by type" },
        format = "<MC...>",
        description = "Returns the company groups with the specified matchcodes."
    )
    public void getCompanyGroupTypesByType(String... matchcodes) throws PnetDataClientException {
        CompanyGroupDataGet query = pnetRestClient.restrict(companyGroupDataClient.get());
        PnetDataClientResultPage<CompanyGroupDataDTO> result = query.allByCompanyGroupTypes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all company group types", description = "Exports all company group types.")
    public void exportAllCompanyGroupTypes() throws PnetDataClientException {
        CompanyGroupTypeDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated company group types",
        format = "[updatedAfter]",
        description = "Exports all company group types updated since yesterday."
    )
    public void exportAllUpdatedCompanyGroupTypes(String updatedAfter) throws PnetDataClientException {
        CompanyGroupTypeDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find company group types by mc", "find company group type by mc" },
        format = "<MC...>",
        description = "Find comany group types by matchcodes."
    )
    public void findCompanyGroupTypes(String... matchcodes) throws PnetDataClientException {
        CompanyGroupTypeDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search company group types", "search company group type" },
        format = "<QUERY>",
        description = "Query company group types."
    )
    public void searchCompanyGroupTypes(String... qs) throws PnetDataClientException {
        CompanyGroupTypeDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<CompanyGroupTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, CompanyGroupTypeItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }
}
