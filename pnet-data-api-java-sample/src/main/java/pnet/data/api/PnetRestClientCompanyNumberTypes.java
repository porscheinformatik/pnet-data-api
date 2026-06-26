package pnet.data.api;

import java.util.Arrays;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataClient;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataDTO;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataFind;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataGet;
import pnet.data.api.companynumbertype.CompanyNumberTypeDataSearch;
import pnet.data.api.companynumbertype.CompanyNumberTypeItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.Table;

public final class PnetRestClientCompanyNumberTypes implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final CompanyNumberTypeDataClient client;

    PnetRestClientCompanyNumberTypes(PnetRestClient pnetRestClient, CompanyNumberTypeDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    @CLI.Command(
        name = { "get company number type by mc", "get company number types by mc" },
        format = "<MC...>",
        description = "Returns the company number types with the specified matchcodes."
    )
    public void getCompanyNumberTypes(String... matchcodes) throws PnetDataClientException {
        CompanyNumberTypeDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<CompanyNumberTypeDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all company number types", description = "Exports all company number types.")
    public void exportAllCompanyNumberTypes() throws PnetDataClientException {
        CompanyNumberTypeDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated company number types",
        format = "[updatedAfter]",
        description = "Exports updated company number types."
    )
    public void exportAllUpdatedCompanyNumberTypes(String updatedAfter) throws PnetDataClientException {
        CompanyNumberTypeDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find company number types by mc", "find company number type by mc" },
        format = "<MC...>",
        description = "Find comany number types by matchcodes."
    )
    public void findCompanyNumberTypes(String... matchcodes) throws PnetDataClientException {
        CompanyNumberTypeDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search company number types", "search company number type" },
        format = "<QUERY>",
        description = "Query company number types."
    )
    public void searchCompanyNumberTypes(String... qs) throws PnetDataClientException {
        CompanyNumberTypeDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<CompanyNumberTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, CompanyNumberTypeItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }
}
