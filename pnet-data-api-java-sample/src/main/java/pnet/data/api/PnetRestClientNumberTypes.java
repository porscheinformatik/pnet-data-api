package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.numbertype.NumberTypeDataClient;
import pnet.data.api.numbertype.NumberTypeDataDTO;
import pnet.data.api.numbertype.NumberTypeDataFind;
import pnet.data.api.numbertype.NumberTypeDataGet;
import pnet.data.api.numbertype.NumberTypeDataSearch;
import pnet.data.api.numbertype.NumberTypeItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictNumberType;
import pnet.data.api.util.Table;

public final class PnetRestClientNumberTypes implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final NumberTypeDataClient client;

    PnetRestClientNumberTypes(PnetRestClient pnetRestClient, NumberTypeDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<String> restrictedNumberTypeMatchcodes = new ArrayList<>();

    @CLI.Command(
        name = { "get number type by mc", "get number types by mc" },
        format = "<MC...>",
        description = "Returns the number types with the specified matchcodes."
    )
    public void getNumberTypes(String... matchcodes) throws PnetDataClientException {
        NumberTypeDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<NumberTypeDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all number types", description = "Exports all number types.")
    public void exportAllNumberTypes() throws PnetDataClientException {
        NumberTypeDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated number types",
        format = "[updatedAfter]",
        description = "Exports all number types updated since yesterday."
    )
    public void exportAllUpdatedNumberTypes(String updatedAfter) throws PnetDataClientException {
        NumberTypeDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find number types by mc", "find number type by mc" },
        format = "<MC...>",
        description = "Find number types by matchcodes."
    )
    public void findNumberTypes(String... matchcodes) throws PnetDataClientException {
        NumberTypeDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search number types", "search number type" },
        format = "<QUERY>",
        description = "Query number types."
    )
    public void searchNumberTypes(String... qs) throws PnetDataClientException {
        NumberTypeDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<NumberTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, NumberTypeItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    @CLI.Command(
        name = { "restrict number types", "restrict number type" },
        format = "<MC...>",
        description = "Places a restriction of number types for subsequent operations."
    )
    public void restrictNumberTypesByMatchcode(String... matchcodes) {
        if (matchcodes != null && matchcodes.length > 0) {
            restrictedNumberTypeMatchcodes.addAll(Arrays.asList(matchcodes));
        }

        pnetRestClient
            .getCli()
            .info("Requests are restricted to number type matchcodes: %s", restrictedNumberTypeMatchcodes);
    }

    @Override
    public void clearRestrictions() {
        restrictedNumberTypeMatchcodes.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (request instanceof RestrictNumberType && !restrictedNumberTypeMatchcodes.isEmpty()) {
            pnetRestClient
                .getCli()
                .info("A restriction for number type matchcodes is in place: %s", restrictedNumberTypeMatchcodes);
            request = ((RestrictNumberType<T>) request).numberTypes(restrictedNumberTypeMatchcodes);
        }
        return request;
    }
}
