package pnet.data.api.client;

import pnet.data.api.Tenant;

/**
 * Adds the opportunity, to switch the tenant with Partner.Net Data API calls.
 *
 * @author ham
 *
 * @param <T> the facade
 */
public interface PnetDataApiContextAware<T>
{

    T withUrl(String url);

    T withTenant(Tenant tenant);

    T withCredentials(String username, String password);

}
