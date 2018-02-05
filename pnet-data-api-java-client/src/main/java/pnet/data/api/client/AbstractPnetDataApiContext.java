package pnet.data.api.client;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.Tenant;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public abstract class AbstractPnetDataApiContext implements PnetDataApiContext
{

    private final PnetDataApiTokenRepository repository;

    public AbstractPnetDataApiContext(PnetDataApiTokenRepository repository)
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
            new PnetDataApiTokenKey(url, key.getPnId(), key.getUsername(), key.getPassword()));
    }

    @Override
    public PnetDataApiContext withTenant(Tenant tenant)
    {
        PnetDataApiTokenKey key = getKey();

        return new DefaultPnetDataApiContext(repository,
            new PnetDataApiTokenKey(key.getUrl(), tenant.getMatchcode(), key.getUsername(), key.getPassword()));
    }

    @Override
    public PnetDataApiContext withCredentials(String username, String password)
    {
        PnetDataApiTokenKey key = getKey();

        return new DefaultPnetDataApiContext(repository,
            new PnetDataApiTokenKey(key.getUrl(), key.getPnId(), username, password));
    }

    @Override
    public RestCall createRestCall() throws PnetDataApiLoginException
    {
        return repository.createRestCall(getKey());
    }

}
