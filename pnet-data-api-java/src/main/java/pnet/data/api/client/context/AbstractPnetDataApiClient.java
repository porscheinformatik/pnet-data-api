package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestResponseException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import pnet.data.api.PnetDataClientException;

/**
 * Abstract base implementation for a rest client.
 *
 * @param <SELF> the type of rest client for fluent interface
 * @author ham
 */
public abstract class AbstractPnetDataApiClient<SELF extends AbstractPnetDataApiClient<SELF>>
    implements PnetDataApiContextAware<SELF> {

    /**
     * Function for a rest call
     *
     * @param <Any> the type of result
     * @author ham
     */
    @FunctionalInterface
    public interface RestCallFunction<Any> {
        Any restCall(RestCall restCall) throws Exception;
    }

    private final PnetDataApiContext context;

    public AbstractPnetDataApiClient(PnetDataApiContext context) {
        super();
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    protected SELF newInstance(PnetDataApiContext context) {
        Constructor<?> constructor;
        try {
            constructor = getClass().getConstructor(PnetDataApiContext.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new UnsupportedOperationException("Necessary constructor in " + getClass() + " is missing", e);
        }

        try {
            return (SELF) constructor.newInstance(context);
        } catch (
            InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException e
        ) {
            throw new IllegalArgumentException("Failed to invoke constructor", e);
        }
    }

    @Override
    public SELF withLoginMethod(PnetDataApiLoginMethod loginMethod) {
        return newInstance(context.withLoginMethod(loginMethod));
    }

    @Override
    @Deprecated
    public SELF withUrl(String url) {
        return newInstance(context.withUrl(url));
    }

    @Override
    @Deprecated
    public SELF withCredentials(String username, String password) {
        return newInstance(context.withCredentials(username, password));
    }

    protected <Any> Any invoke(RestCallFunction<Any> fn) throws PnetDataClientException {
        return invoke(fn, true);
    }

    protected <Any> Any invoke(RestCallFunction<Any> fn, boolean retryOnUnauthorized) throws PnetDataClientException {
        RestCall restCall = context.restCall();

        try {
            return fn.restCall(restCall);
        } catch (RestResponseException e) {
            if (e.getStatusCode() == 401 && retryOnUnauthorized) {
                context.invalidateLogin();

                return invoke(fn, false);
            }

            throw new PnetDataClientException("REST response error", e);
        } catch (PnetDataClientException e) {
            if (e.getStatusCode() == 401 && retryOnUnauthorized) {
                context.invalidateLogin();

                return invoke(fn, false);
            }

            throw new PnetDataClientException("REST client error", e);
        } catch (Exception e) {
            throw new PnetDataClientException("REST call failed", e);
        }
    }
}
