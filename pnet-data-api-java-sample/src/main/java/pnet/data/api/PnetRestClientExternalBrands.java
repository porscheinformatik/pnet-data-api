package pnet.data.api;

import java.util.Arrays;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.externalbrand.ExternalBrandDataClient;
import pnet.data.api.externalbrand.ExternalBrandDataDTO;
import pnet.data.api.externalbrand.ExternalBrandDataFind;
import pnet.data.api.externalbrand.ExternalBrandDataGet;
import pnet.data.api.externalbrand.ExternalBrandDataSearch;
import pnet.data.api.externalbrand.ExternalBrandItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Table;

public final class PnetRestClientExternalBrands implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final ExternalBrandDataClient client;

    PnetRestClientExternalBrands(PnetRestClient pnetRestClient, ExternalBrandDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    @CLI.Command(
        name = { "get external brand by mc", "get external brands by mc" },
        format = "<MC...>",
        description = "Returns the external brands with the specified matchcodes."
    )
    public void getExternalBrands(String... matchcodes) throws PnetDataClientException {
        ExternalBrandDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<ExternalBrandDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all external brands", description = "Exports all external brands.")
    public void exportAllExternalBrands() throws PnetDataClientException {
        ExternalBrandDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated external brands",
        format = "[updatedAfter]",
        description = "Exports all external brands updated since yesterday."
    )
    public void exportAllUpdatedExternalBrands(String updatedAfter) throws PnetDataClientException {
        ExternalBrandDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find external brands by mc", "find external brand by mc" },
        format = "<MC...>",
        description = "Find external brands by matchcodes."
    )
    public void findExternalBrands(String... matchcodes) throws PnetDataClientException {
        ExternalBrandDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search external brands", "search external brand" },
        format = "<QUERY>",
        description = "Query external brands."
    )
    public void searchExternalBrands(String... qs) throws PnetDataClientException {
        ExternalBrandDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<ExternalBrandItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, ExternalBrandItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }
}
