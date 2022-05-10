package pnet.data.api.client.context;

/**
 * @author ham
 * @param <T> the facade
 */
public interface PnetDataApiContextAware<T>
{
    @Deprecated
    T withUrl(String url);

    @Deprecated
    T withCredentials(String username, String password);
}
