package pnet.data.api.client.context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.client.PnetDataClientLoginException;

/**
 * Abstract base implementation for a rest client.
 *
 * @author ham
 * @param <SELF> the type of rest client for fluent interface
 */
public abstract class AbstractPnetDataApiClient<SELF extends AbstractPnetDataApiClient<SELF>>
    implements PnetDataApiContextAware<SELF>
{

    private final ObjectMapper mapper;
    private final PnetDataApiContext context;

    public AbstractPnetDataApiClient(ObjectMapper mapper, PnetDataApiContext context)
    {
        super();

        this.mapper = mapper;
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    protected SELF newInstance(PnetDataApiContext context)
    {
        Constructor<?> constructor;
        try
        {
            constructor = getClass().getConstructor(ObjectMapper.class, PnetDataApiContext.class);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try
        {
            return (SELF) constructor.newInstance(mapper, context);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            throw new IllegalArgumentException("Failed to invoke constructor", e);
        }
    }

    @Override
    public SELF withUrl(String url)
    {
        return newInstance(context.withUrl(url));
    }

    @Override
    public SELF withTenant(String tenant)
    {
        return newInstance(context.withTenant(tenant));
    }

    @Override
    public SELF withCredentials(String username, String password)
    {
        return newInstance(context.withCredentials(username, password));
    }

    protected RestCall createRestCall() throws PnetDataClientLoginException
    {
        return context.createRestCall();
    }

    protected ObjectMapper getMapper()
    {
        return mapper;
    }

}
