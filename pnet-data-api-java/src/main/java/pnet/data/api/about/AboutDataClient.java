package pnet.data.api.about;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pnet.data.api.PnetDataApiException;
import pnet.data.api.activity.ActivityDataDTO;
import pnet.data.api.client.context.AbstractPnetDataApiClient;
import pnet.data.api.client.context.PnetDataApiContext;

/**
 * Data-API client for {@link ActivityDataDTO}s.
 *
 * @author ham
 */
@Service
public class AboutDataClient extends AbstractPnetDataApiClient<AboutDataClient>
{

    @Autowired
    public AboutDataClient(PnetDataApiContext context)
    {
        super(context);
    }

    public AboutDataDTO about() throws PnetDataApiException
    {
        return invoke(restCall -> restCall.get("/api/v1/about/", AboutDataDTO.class));
    }
}
