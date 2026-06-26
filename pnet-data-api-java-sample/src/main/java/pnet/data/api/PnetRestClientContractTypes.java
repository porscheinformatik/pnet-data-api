package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.contracttype.ContractTypeDataClient;
import pnet.data.api.contracttype.ContractTypeDataDTO;
import pnet.data.api.contracttype.ContractTypeDataFind;
import pnet.data.api.contracttype.ContractTypeDataGet;
import pnet.data.api.contracttype.ContractTypeDataSearch;
import pnet.data.api.contracttype.ContractTypeItemDTO;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictContractType;
import pnet.data.api.util.Table;

public final class PnetRestClientContractTypes implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final ContractTypeDataClient client;

    PnetRestClientContractTypes(PnetRestClient pnetRestClient, ContractTypeDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<String> restrictedContractTypeMatchcodes = new ArrayList<>();

    @CLI.Command(
        name = { "get contract type by mc", "get contract types by mc" },
        format = "<MC...>",
        description = "Returns the contract types with the specified matchcodes."
    )
    public void getContractTypes(String... matchcodes) throws PnetDataClientException {
        ContractTypeDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<ContractTypeDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all contract types", description = "Exports all contract types.")
    public void exportAllContractTypes() throws PnetDataClientException {
        ContractTypeDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated contract types",
        format = "[updatedAfter]",
        description = "Exports all contract types updated since yesterday."
    )
    public void exportAllUpdatedContractTypes(String updatedAfter) throws PnetDataClientException {
        ContractTypeDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find contract types by mc", "find contract type by mc" },
        format = "<MC...>",
        description = "Find contract types by matchcodes."
    )
    public void findContractTypes(String... matchcodes) throws PnetDataClientException {
        ContractTypeDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search contract types", "search contract type" },
        format = "<QUERY>",
        description = "Query contract types."
    )
    public void searchContractTypes(String... qs) throws PnetDataClientException {
        ContractTypeDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<ContractTypeItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    private void populateTable(Table table, ContractTypeItemDTO dto) {
        table.addRow(
            dto.getMatchcode(),
            dto.getLabel(),
            dto.getType(),
            dto.getTenants(),
            dto.getBrands(),
            dto.getLastUpdate(),
            dto.getScore()
        );
    }

    @CLI.Command(
        name = { "restrict contract types", "restrict contract type" },
        format = "<MC...>",
        description = "Places a restriction of contract types for subsequent operations."
    )
    public void restrictContractTypesByMatchcode(String... matchcodes) {
        if (matchcodes != null && matchcodes.length > 0) {
            restrictedContractTypeMatchcodes.addAll(Arrays.asList(matchcodes));
        }

        pnetRestClient
            .getCli()
            .info("Requests are restricted to contract type matchcodes: %s", restrictedContractTypeMatchcodes);
    }

    @Override
    public void clearRestrictions() {
        restrictedContractTypeMatchcodes.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (request instanceof RestrictContractType && !restrictedContractTypeMatchcodes.isEmpty()) {
            pnetRestClient
                .getCli()
                .info("A restriction for contract type matchcodes is in place: %s", restrictedContractTypeMatchcodes);
            request = ((RestrictContractType<T>) request).contractTypes(restrictedContractTypeMatchcodes);
        }
        return request;
    }
}
