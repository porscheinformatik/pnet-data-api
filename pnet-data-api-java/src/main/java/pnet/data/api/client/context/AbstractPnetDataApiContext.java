package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.client.PnetDataClientLoginException;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public abstract class AbstractPnetDataApiContext implements PnetDataApiContext
{

    private final PnetDataApiTokenRepository repository;

    protected AbstractPnetDataApiContext(PnetDataApiTokenRepository repository)
    {
        super();

        this.repository = repository;
    }

    protected abstract PnetDataApiTokenKey getKey();

    @Override
    public PnetDataApiContext withUrl(String url)
    {
        PnetDataApiTokenKey key = getKey();

        return new DefaultPnetDataApiContext(repository,
            new PnetDataApiTokenKey(url, key.getTenant(), key.getUsername(), key.getPassword()));
    }

    @Override
    public PnetDataApiContext withTenant(String tenant)
    {
        PnetDataApiTokenKey key = getKey();

        return new DefaultPnetDataApiContext(repository,
            new PnetDataApiTokenKey(key.getUrl(), tenant, key.getUsername(), key.getPassword()));
    }

    @Override
    public PnetDataApiContext withCredentials(String username, String password)
    {
        PnetDataApiTokenKey key = getKey();

        return new DefaultPnetDataApiContext(repository,
            new PnetDataApiTokenKey(key.getUrl(), key.getTenant(), username, password));
    }

    @Override
    public RestCall createRestCall() throws PnetDataClientLoginException
    {
        return repository.createRestCall(getKey());
    }

}
