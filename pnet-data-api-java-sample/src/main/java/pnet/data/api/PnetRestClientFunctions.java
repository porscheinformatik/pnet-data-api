package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.function.FunctionAutoCompleteDTO;
import pnet.data.api.function.FunctionDataAutoComplete;
import pnet.data.api.function.FunctionDataClient;
import pnet.data.api.function.FunctionDataDTO;
import pnet.data.api.function.FunctionDataFind;
import pnet.data.api.function.FunctionDataGet;
import pnet.data.api.function.FunctionDataSearch;
import pnet.data.api.function.FunctionItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictFunction;
import pnet.data.api.util.Table;

public final class PnetRestClientFunctions implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final FunctionDataClient client;

    PnetRestClientFunctions(PnetRestClient pnetRestClient, FunctionDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<String> restrictedFunctionMatchcodes = new ArrayList<>();

    @CLI.Command(
        name = { "get function by mc", "get functions by mc" },
        format = "<MC...>",
        description = "Returns the functions with the specified matchcodes."
    )
    public void getFunctions(String... matchcodes) throws PnetDataClientException {
        FunctionDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<FunctionDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all functions", description = "Exports all functions.")
    public void exportAllFunctions() throws PnetDataClientException {
        FunctionDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<FunctionItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated functions",
        format = "[updatedAfter]",
        description = "Exports all functions updated since yesterday."
    )
    public void exportAllUpdatedFunctions(String updatedAfter) throws PnetDataClientException {
        FunctionDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<FunctionItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find functions by mc", "find function by mc" },
        format = "<MC...>",
        description = "Find functions by matchcodes."
    )
    public void findFunctions(String... matchcodes) throws PnetDataClientException {
        FunctionDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(name = { "search functions", "search function" }, format = "<QUERY>", description = "Query functions.")
    public void searchFunctions(String... qs) throws PnetDataClientException {
        FunctionDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<FunctionItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "auto complete functions", "auto complete function" },
        format = "<QUERY>",
        description = "Auto complete the name of functions."
    )
    public void autoCompleteFunctions(String... qs) throws PnetDataClientException {
        FunctionDataAutoComplete query = pnetRestClient.restrict(client.autoComplete());
        List<FunctionAutoCompleteDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateAutoComplete);
    }

    private void populateAutoComplete(Table table, FunctionAutoCompleteDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getScore());
    }

    private void populateTable(Table table, FunctionItemDTO dto) {
        table.addRow(
            dto.getMatchcode(),
            dto.getLabel(),
            dto.getDescription(),
            dto.getTenants(),
            dto.getBrands(),
            dto.getLastUpdate(),
            dto.getScore()
        );
    }

    @CLI.Command(
        name = { "restrict functions", "restrict function" },
        format = "<MC...>",
        description = "Places a restriction of functions for subsequent operations."
    )
    public void restrictFunctionsByMatchcode(String... matchcodes) {
        if (matchcodes != null && matchcodes.length > 0) {
            restrictedFunctionMatchcodes.addAll(Arrays.asList(matchcodes));
        }

        pnetRestClient
            .getCli()
            .info("Requests are restricted to function matchcodes: %s", restrictedFunctionMatchcodes);
    }

    @Override
    public void clearRestrictions() {
        restrictedFunctionMatchcodes.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (request instanceof RestrictFunction && !restrictedFunctionMatchcodes.isEmpty()) {
            pnetRestClient
                .getCli()
                .info("A restriction for function matchcodes is in place: %s", restrictedFunctionMatchcodes);
            request = ((RestrictFunction<T>) request).functions(restrictedFunctionMatchcodes);
        }
        return request;
    }
}
