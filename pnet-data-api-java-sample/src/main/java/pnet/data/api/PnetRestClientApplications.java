package pnet.data.api;

import java.util.Arrays;
import pnet.data.api.application.ApplicationDataClient;
import pnet.data.api.application.ApplicationDataDTO;
import pnet.data.api.application.ApplicationDataFind;
import pnet.data.api.application.ApplicationDataGet;
import pnet.data.api.application.ApplicationDataSearch;
import pnet.data.api.application.ApplicationItemDTO;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Table;

public final class PnetRestClientApplications implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final ApplicationDataClient client;

    PnetRestClientApplications(PnetRestClient pnetRestClient, ApplicationDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    @CLI.Command(
        name = { "get application by mc", "get applications by mc" },
        format = "<MC...>",
        description = "Returns the applications with the specified matchcodes."
    )
    public void getApplications(String... matchcodes) throws PnetDataClientException {
        ApplicationDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<ApplicationDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all applications", description = "Exports all applications.")
    public void exportAllApplications() throws PnetDataClientException {
        ApplicationDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<ApplicationItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated applications",
        format = "[updatedAfter]",
        description = "Exports all applications updated since yesterday."
    )
    public void exportAllUpdatedApplications(String updatedAfter) throws PnetDataClientException {
        ApplicationDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<ApplicationItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find applications by mc", "find application by mc" },
        format = "<MC...>",
        description = "Find applications by matchcodes."
    )
    public void findApplications(String... matchcodes) throws PnetDataClientException {
        ApplicationDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search applications", "search application" },
        format = "<QUERY>",
        description = "Query applications."
    )
    public void searchApplications(String... qs) throws PnetDataClientException {
        ApplicationDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<ApplicationItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, ApplicationItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }
}
