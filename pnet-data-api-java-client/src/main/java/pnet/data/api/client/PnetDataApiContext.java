package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.Tenant;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public interface PnetDataApiContext
{

    PnetDataApiContext withUrl(String url);

    PnetDataApiContext withTenant(Tenant tenant);

    PnetDataApiContext withCredentials(String username, String password);

    RestCall createRestCall() throws PnetDataApiLoginException;

}