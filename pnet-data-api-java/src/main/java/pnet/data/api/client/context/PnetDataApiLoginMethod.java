package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import pnet.data.api.PnetDataClientException;

public interface PnetDataApiLoginMethod
{
    /**
     * Performs a login and returns a {@link RestCall} object, that can be used for successive requests.
     *
     * @param factory the factory for rest calls
     * @return a {@link RestCall} object, that can be used for successive requests
     * @throws PnetDataClientException on occasion
     */
    RestCall performLogin(RestCallFactory factory) throws PnetDataClientException;
}
