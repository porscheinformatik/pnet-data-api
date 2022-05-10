package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.PnetDataClientException;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public abstract class AbstractPnetDataApiContext implements PnetDataApiContext
{
    protected final PnetDataApiTokenRepository repository;

    protected AbstractPnetDataApiContext(PnetDataApiTokenRepository repository)
    {
        super();

        this.repository = repository;
    }

    protected abstract PnetDataApiLoginMethod getLoginMethod();

    @Override
    public RestCall restCall() throws PnetDataClientException
    {
        return repository.restCall(getLoginMethod());
    }

    @Override
    public void invalidateLogin() throws PnetDataClientException
    {
        repository.invalidate(getLoginMethod());
    }
}
