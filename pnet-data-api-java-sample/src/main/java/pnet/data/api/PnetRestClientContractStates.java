package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.contractstate.ContractStateDataClient;
import pnet.data.api.contractstate.ContractStateDataDTO;
import pnet.data.api.contractstate.ContractStateDataFind;
import pnet.data.api.contractstate.ContractStateDataGet;
import pnet.data.api.contractstate.ContractStateDataSearch;
import pnet.data.api.contractstate.ContractStateItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictContractState;
import pnet.data.api.util.Table;

public final class PnetRestClientContractStates implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final ContractStateDataClient client;

    PnetRestClientContractStates(PnetRestClient pnetRestClient, ContractStateDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<String> restrictedContractStateMatchcodes = new ArrayList<>();

    @CLI.Command(
        name = { "get contract state by mc", "get contract states by mc" },
        format = "<MC...>",
        description = "Returns the contract states with the specified matchcodes."
    )
    public void getContractStates(String... matchcodes) throws PnetDataClientException {
        ContractStateDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<ContractStateDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all contract states", description = "Exports all contract states.")
    public void exportAllContractStates() throws PnetDataClientException {
        ContractStateDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated contract states",
        format = "[updatedAfter]",
        description = "Exports all contract states updated since yesterday."
    )
    public void exportAllUpdatedContractStates(String updatedAfter) throws PnetDataClientException {
        ContractStateDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find contract states by mc", "find contract state by mc" },
        format = "<MC...>",
        description = "Find contract states by matchcodes."
    )
    public void findContractStates(String... matchcodes) throws PnetDataClientException {
        ContractStateDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search contract states", "search contract state" },
        format = "<QUERY>",
        description = "Query contract states types."
    )
    public void searchContractStates(String... qs) throws PnetDataClientException {
        ContractStateDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<ContractStateItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, ContractStateItemDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getLastUpdate(), dto.getScore());
    }

    @CLI.Command(
        name = { "restrict contract states", "restrict contract state" },
        format = "<MC...>",
        description = "Places a restriction of contract states for subsequent operations."
    )
    public void restrictContractStatesByMatchcode(String... matchcodes) {
        if (matchcodes != null && matchcodes.length > 0) {
            restrictedContractStateMatchcodes.addAll(Arrays.asList(matchcodes));
        }

        pnetRestClient
            .getCli()
            .info("Requests are restricted to contract state matchcodes: %s", restrictedContractStateMatchcodes);
    }

    @Override
    public void clearRestrictions() {
        restrictedContractStateMatchcodes.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (request instanceof RestrictContractState && !restrictedContractStateMatchcodes.isEmpty()) {
            pnetRestClient
                .getCli()
                .info("A restriction for contract state matchcodes is in place: %s", restrictedContractStateMatchcodes);
            request = ((RestrictContractState<T>) request).contractStates(restrictedContractStateMatchcodes);
        }
        return request;
    }
}
