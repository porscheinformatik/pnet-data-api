package pnet.data.api.resource;

import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestResponseException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;
import pnet.data.api.util.Resource;

@Service
public class ResourceDataClient extends AbstractPnetDataApiClient<ResourceDataClient> {

    public ResourceDataClient(PnetDataApiContext context) {
        super(context);
    }

    public Optional<Resource> resource(String uuid) throws PnetDataClientException {
        return invoke(restCall -> {
            try {
                RestResponse<byte[]> response = restCall
                    .variable("uuid", uuid)
                    .path("api/v1/resources/stream/{uuid}")
                    .invoke(RestMethod.GET, byte[].class);

                return Optional.of(new Resource(response.getContentType().toString(), response.getBody()));
            } catch (RestResponseException e) {
                if (e.getStatusCode() == 404) {
                    return Optional.empty();
                }

                throw new PnetDataClientException("Resource Request for uuid " + uuid + " failed", e);
            }
        });
    }
}

