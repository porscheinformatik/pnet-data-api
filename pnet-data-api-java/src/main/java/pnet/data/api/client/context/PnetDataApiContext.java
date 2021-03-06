package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.PnetDataClientException;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public interface PnetDataApiContext
{

    PnetDataApiContext withUrl(String url);

    PnetDataApiContext withCredentials(String username, String password);

    RestCall restCall() throws PnetDataClientException;

    void invalidateLogin() throws PnetDataClientException;

}