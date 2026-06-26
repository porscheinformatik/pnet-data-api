package pnet.data.api;

import java.util.Arrays;


import pnet.data.api.advisortype.AdvisorTypeDataClient;
import pnet.data.api.advisortype.AdvisorTypeDataDTO;
import pnet.data.api.advisortype.AdvisorTypeDataFind;
import pnet.data.api.advisortype.AdvisorTypeDataGet;
import pnet.data.api.advisortype.AdvisorTypeDataSearch;
import pnet.data.api.advisortype.AdvisorTypeItemDTO;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Table;

public final class PnetRestClientAdvisorTypes implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final AdvisorTypeDataClient client;

    PnetRestClientAdvisorTypes(PnetRestClient pnetRestClient, AdvisorTypeDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    @CLI.Command(
        name = { "get advisor type by mc", "get advisor types by mc" },
        format = "<MC...>",
        description = "Returns the advisor types with the specified matchcodes."
    )
    public void getAdvisorTypes(String... matchcodes) throws PnetDataClientException {
        AdvisorTypeDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<AdvisorTypeDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all advisor types", description = "Exports all advisor types.")
    public void exportAllAdvisorTypes() throws PnetDataClientException {
        AdvisorTypeDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated advisor types",
        format = "[updatedAfter]",
        description = "Exports all advisor types updated since yesterday."
    )
    public void exportAllUpdatedAdvisorTypes(String updatedAfter) throws PnetDataClientException {
        AdvisorTypeDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find advisor types by mc", "find advisor type by mc" },
        format = "<MC...>",
        description = "Find advisor types by matchcodes."
    )
    public void findAdvisorTypes(String... matchcodes) throws PnetDataClientException {
        AdvisorTypeDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search advisor types", "search advisor type" },
        format = "<QUERY>",
        description = "Query advisor types."
    )
    public void searchAdvisorTypes(String... qs) throws PnetDataClientException {
        AdvisorTypeDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<AdvisorTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, AdvisorTypeItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getLastUpdate(), dto.getScore());
    }
}
