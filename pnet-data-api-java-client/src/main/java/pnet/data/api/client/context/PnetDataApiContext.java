package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.client.PnetDataClientLoginException;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public interface PnetDataApiContext
{

    PnetDataApiContext withUrl(String url);

    PnetDataApiContext withTenant(String tenant);

    PnetDataApiContext withCredentials(String username, String password);

    RestCall createRestCall() throws PnetDataClientLoginException;

}