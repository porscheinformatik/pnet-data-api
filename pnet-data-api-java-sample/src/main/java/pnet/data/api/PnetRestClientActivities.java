package pnet.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import pnet.data.api.activity.ActivityAutoCompleteDTO;
import pnet.data.api.activity.ActivityDataAutoComplete;
import pnet.data.api.activity.ActivityDataClient;
import pnet.data.api.activity.ActivityDataDTO;
import pnet.data.api.activity.ActivityDataFind;
import pnet.data.api.activity.ActivityDataGet;
import pnet.data.api.activity.ActivityDataSearch;
import pnet.data.api.activity.ActivityItemDTO;
import pnet.data.api.client.PnetDataClientResultPage;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Restrict;
import pnet.data.api.util.RestrictActivity;
import pnet.data.api.util.Table;

public final class PnetRestClientActivities implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final ActivityDataClient client;

    PnetRestClientActivities(PnetRestClient pnetRestClient, ActivityDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    private final List<String> restrictedActivityMatchcodes = new ArrayList<>();

    @CLI.Command(
        name = { "get activity by mc", "get activities by mc" },
        format = "<MC...>",
        description = "Returns the activities with the specified matchcodes."
    )
    public void getActivities(String... matchcodes) throws PnetDataClientException {
        ActivityDataGet query = pnetRestClient.restrict(client.get());
        PnetDataClientResultPage<ActivityDataDTO> result = query.allByMatchcodes(
            Arrays.asList(matchcodes),
            pnetRestClient.getPage(),
            pnetRestClient.getPageSize()
        );

        pnetRestClient.printResults(result, null);
    }

    @CLI.Command(name = "export all activities", description = "Exports all activities.")
    public void exportAllActivities() throws PnetDataClientException {
        ActivityDataFind query = pnetRestClient.restrict(client.find());
        PnetDataClientResultPage<ActivityItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = "export updated activities",
        format = "[updatedAfter]",
        description = "Exports all activities updated since yesterday."
    )
    public void exportAllUpdatedActivities(String updatedAfter) throws PnetDataClientException {
        ActivityDataFind query = pnetRestClient.restrict(
            client.find().updatedAfter(PnetRestClient.parseUpdatedAfter(updatedAfter))
        );
        PnetDataClientResultPage<ActivityItemDTO> result = pnetRestClient.isScroll()
            ? query.executeAndScroll(pnetRestClient.getLanguage(), pnetRestClient.getPageSize())
            : query.execute(pnetRestClient.getLanguage(), pnetRestClient.getPage(), pnetRestClient.getPageSize());

        pnetRestClient.printAllResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "find activities by mc", "find activity by mc" },
        format = "<MC...>",
        description = "Find activities by matchcodes."
    )
    public void findActivities(String... matchcodes) throws PnetDataClientException {
        ActivityDataFind query = pnetRestClient.restrict(client.find().matchcode(matchcodes));
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(pnetRestClient.getLanguage());

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "search activities", "search activity" },
        format = "<QUERY>",
        description = "Query activities."
    )
    public void searchActivities(String... qs) throws PnetDataClientException {
        ActivityDataSearch query = pnetRestClient.restrict(client.search());
        PnetDataClientResultPage<ActivityItemDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateTable);
    }

    @CLI.Command(
        name = { "auto complete activities", "auto complete activity" },
        format = "<QUERY>",
        description = "Auto complete the name of activities."
    )
    public void autoCompleteActivities(String... qs) throws PnetDataClientException {
        ActivityDataAutoComplete query = pnetRestClient.restrict(client.autoComplete());
        List<ActivityAutoCompleteDTO> result = query.execute(
            pnetRestClient.getLanguage(),
            PnetRestClient.joinQuery(qs)
        );

        pnetRestClient.printResults(result, this::populateAutoComplete);
    }

    private void populateAutoComplete(Table table, ActivityAutoCompleteDTO dto) {
        table.addRow(dto.getMatchcode(), dto.getLabel(), dto.getDescription(), dto.getScore());
    }

    private void populateTable(Table table, ActivityItemDTO dto) {
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
        name = { "restrict activities", "restrict activity" },
        format = "<MC...>",
        description = "Places a restriction of activities for subsequent operations."
    )
    public void restrictActivitiesByMatchcode(String... matchcodes) {
        if (matchcodes != null && matchcodes.length > 0) {
            restrictedActivityMatchcodes.addAll(Arrays.asList(matchcodes));
        }

        pnetRestClient
            .getCli()
            .info("Requests are restricted to activity matchcodes: %s", restrictedActivityMatchcodes);
    }

    @Override
    public void clearRestrictions() {
        restrictedActivityMatchcodes.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Restrict<T>> T applyRestrictions(T request) {
        if (request instanceof RestrictActivity && !restrictedActivityMatchcodes.isEmpty()) {
            pnetRestClient
                .getCli()
                .info("A restriction for activity matchcodes is in place: %s", restrictedActivityMatchcodes);
            request = ((RestrictActivity<T>) request).activities(restrictedActivityMatchcodes);
        }
        return request;
    }
}
