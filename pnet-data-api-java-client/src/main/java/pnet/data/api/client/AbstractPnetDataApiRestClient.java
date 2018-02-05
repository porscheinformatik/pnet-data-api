package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.Tenant;

/**
 * Abstract base implementation for a rest client.
 *
 * @author ham
 * @param <T> the type of rest client for fluent interface
 */
public abstract class AbstractPnetDataApiRestClient<T> implements PnetDataApiContextAware<T>
{

    private final PnetDataApiContext context;

    public AbstractPnetDataApiRestClient(PnetDataApiContext context)
    {
        super();

        this.context = context;
    }

    protected abstract T newInstance(PnetDataApiContext context);

    @Override
    public T withUrl(String url)
    {
        return newInstance(context.withUrl(url));
    }

    @Override
    public T withTenant(Tenant tenant)
    {
        return newInstance(context.withTenant(tenant));
    }

    @Override
    public T withCredentials(String username, String password)
    {
        return newInstance(context.withCredentials(username, password));
    }

    protected RestCall createRestCall() throws PnetDataApiLoginException
    {
        return context.createRestCall();
    }

}
