package pnet.data.api;

import java.util.Optional;


import pnet.data.api.resource.ResourceDataClient;
import pnet.data.api.util.CLI;
import pnet.data.api.util.Resource;

public final class PnetRestClientResources implements PnetRestClientModule {

    private final PnetRestClient pnetRestClient;
    private final ResourceDataClient client;

    PnetRestClientResources(PnetRestClient pnetRestClient, ResourceDataClient client) {
        this.pnetRestClient = pnetRestClient;
        this.client = client;
    }

    @CLI.Command(name = "get resource by uuid", format = "<UUID>", description = "Shows resource with uuid")
    public void getResourceByUuid(String uuid) throws PnetDataClientException {
        Optional<Resource> resource = client.resource(uuid);

        if (resource.isEmpty()) {
            pnetRestClient.getCli().error("Resource not found.");
            return;
        }

        if (resource.get().getType().startsWith("image")) {
            PnetRestClient.showImage("Resource " + uuid, resource.get().toImage());
        } else {
            pnetRestClient.getCli().info("Resource %s data: %s", uuid, resource.get().getData());
        }
    }
}
