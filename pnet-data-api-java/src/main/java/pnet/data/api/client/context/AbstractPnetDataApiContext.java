package pnet.data.api.client.context;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.client.PnetDataClientLoginException;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public abstract class AbstractPnetDataApiContext implements PnetDataApiContext
{

    private final ObjectMapper mapper;
    private final PnetDataApiTokenRepository repository;

    protected AbstractPnetDataApiContext(ObjectMapper mapper, PnetDataApiTokenRepository repository)
    {
        super();
        
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public ObjectMapper getMapper()
    {
        return mapper;
    }

    protected abstract PnetDataApiTokenKey getKey();

    @Override
    public PnetDataApiContext withUrl(String url)
    {
        PnetDataApiTokenKey key = getKey();

        return new DefaultPnetDataApiContext(mapper, repository,
            new PnetDataApiTokenKey(url, key.getTenant(), key.getUsername(), key.getPassword()));
    }

    @Override
    public PnetDataApiContext withTenant(String tenant)
    {
        PnetDataApiTokenKey key = getKey();

        return new DefaultPnetDataApiContext(mapper, repository,
            new PnetDataApiTokenKey(key.getUrl(), tenant, key.getUsername(), key.getPassword()));
    }

    @Override
    public PnetDataApiContext withCredentials(String username, String password)
    {
        PnetDataApiTokenKey key = getKey();

        return new DefaultPnetDataApiContext(mapper, repository,
            new PnetDataApiTokenKey(key.getUrl(), key.getTenant(), username, password));
    }

    @Override
    public RestCall createRestCall() throws PnetDataClientLoginException
    {
        return repository.createRestCall(getKey());
    }

}
