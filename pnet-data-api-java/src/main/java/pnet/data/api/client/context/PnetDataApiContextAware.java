package pnet.data.api.client.context;

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

    T withCredentials(String username, String password);

}
