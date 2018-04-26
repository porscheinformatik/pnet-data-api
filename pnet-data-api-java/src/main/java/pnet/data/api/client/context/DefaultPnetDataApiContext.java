package pnet.data.api.client.context;

import com.fasterxml.jackson.databind.ObjectMapper;

import pnet.data.api.client.jackson.JacksonPnetDataApiModule;

/**
 * Default implementation of the {@link PnetDataApiContext}.
 *
 * @author ham
 */
public class DefaultPnetDataApiContext extends AbstractPnetDataApiContext
{

    private final PnetDataApiTokenKey key;

    public DefaultPnetDataApiContext(PnetDataApiTokenRepository repository, PnetDataApiTokenKey key)
    {
        this(JacksonPnetDataApiModule.createObjectMapper(), repository, key);
    }

    protected DefaultPnetDataApiContext(ObjectMapper mapper, PnetDataApiTokenRepository repository,
        PnetDataApiTokenKey key)
    {
        super(mapper, repository);

        this.key = key;
    }

    @Override
    protected PnetDataApiTokenKey getKey()
    {
        return key;
    }

}
