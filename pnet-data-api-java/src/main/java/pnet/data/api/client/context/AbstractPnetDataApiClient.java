package pnet.data.api.client.context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestResponseException;
import pnet.data.api.PnetDataApiException;
import pnet.data.api.client.PnetDataClientException;

/**
 * Abstract base implementation for a rest client.
 *
 * @author ham
 * @param <SELF> the type of rest client for fluent interface
 */
public abstract class AbstractPnetDataApiClient<SELF extends AbstractPnetDataApiClient<SELF>>
    implements PnetDataApiContextAware<SELF>
{

    /**
     * Function for a rest call
     *
     * @author ham
     * @param <Any> the type of result
     */
    @FunctionalInterface
    public interface RestCallFunction<Any>
    {
        Any restCall(RestCall restCall) throws Exception;
    }

    private final PnetDataApiContext context;

    public AbstractPnetDataApiClient(PnetDataApiContext context)
    {
        super();

        this.context = context;
    }

    @SuppressWarnings("unchecked")
    protected SELF newInstance(PnetDataApiContext context)
    {
        Constructor<?> constructor;
        try
        {
            constructor = getClass().getConstructor(PnetDataApiContext.class);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try
        {
            return (SELF) constructor.newInstance(context);
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
    public SELF withCredentials(String username, String password)
    {
        return newInstance(context.withCredentials(username, password));
    }

    protected <Any> Any invoke(RestCallFunction<Any> fn) throws PnetDataApiException
    {
        return invoke(fn, true);
    }

    protected <Any> Any invoke(RestCallFunction<Any> fn, boolean retryOnUnauthorized) throws PnetDataApiException
    {
        RestCall restCall = context.restCall();

        try
        {
            return fn.restCall(restCall);
        }
        catch (RestResponseException e)
        {
            if (e.getStatusCode() == 401 && retryOnUnauthorized)
            {
                context.invalidateLogin();

                return invoke(fn, false);
            }

            throw new PnetDataClientException("REST call failed", e);
        }
        catch (Exception | Error e)
        {
            throw new PnetDataClientException("REST call failed", e);
        }
    }

}
