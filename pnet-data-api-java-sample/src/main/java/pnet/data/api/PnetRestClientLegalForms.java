package pnet.data.api;

import java.util.Arrays;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.legalform.LegalFormDataClient;
import pnet.data.api.legalform.LegalFormDataDTO;
import pnet.data.api.legalform.LegalFormDataFind;
import pnet.data.api.legalform.LegalFormDataGet;
import pnet.data.api.legalform.LegalFormDataSearch;
import pnet.data.api.legalform.LegalFormItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Table;

public final class PnetRestClientLegalForms implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final LegalFormDataClient client;

    PnetRestClientLegalForms(PnetRestClient pnetRestClient, LegalFormDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    @CLI.Command(
        name = { "get legal form by mc", "get legal forms by mc" },
        format = "<MC...>",
        description = "Returns the legal forms with the specified matchcodes."
    )
    public void getLegalForms(String... matchcodes) throws PnetDataClientException {
        LegalFormDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<LegalFormDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all legal forms", description = "Exports all legal forms.")
    public void exportAllLegalForms() throws PnetDataClientException {
        LegalFormDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated legal forms",
        format = "[updatedAfter]",
        description = "Exports all legal forms updated since yesterday."
    )
    public void exportAllUpdatedLegalForms(String updatedAfter) throws PnetDataClientException {
        LegalFormDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find legal forms by mc", "find legal form by mc" },
        format = "<MC...>",
        description = "Find comany group types by matchcodes."
    )
    public void findLegalForms(String... matchcodes) throws PnetDataClientException {
        LegalFormDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search legal forms", "search legal form" },
        format = "<QUERY>",
        description = "Query legal forms."
    )
    public void searchLegalForms(String... qs) throws PnetDataClientException {
        LegalFormDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<LegalFormItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, LegalFormItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }
}
