package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import pnet.data.api.PnetDataClientException;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 * @deprecated use the {@link SimplePnetDataApiContext} instead, since it does not depend on a repository.
 */
@Deprecated
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
    @Deprecated
    public PnetDataApiContext withUrl(String url)
    {
        PnetDataApiLoginMethod loginMethod = getLoginMethod();

        if (loginMethod instanceof AuthenticationTokenPnetDataApiLoginMethod)
        {
            return withLoginMethod(((AuthenticationTokenPnetDataApiLoginMethod) loginMethod).withUrl(url));
        }

        if (loginMethod instanceof UsernamePasswordPnetDataApiLoginMethod)
        {
            return withLoginMethod(((UsernamePasswordPnetDataApiLoginMethod) loginMethod).withUrl(url));
        }

        if (loginMethod instanceof PnetDataApiTokenKey)
        {
            return withLoginMethod(((PnetDataApiTokenKey) loginMethod).withUrl(url));
        }

        throw new UnsupportedOperationException(
            "This method is deprecated and does not support loginMethods of type " + loginMethod.getClass());
    }

    @Override
    @Deprecated
    public PnetDataApiContext withCredentials(String username, String password)
    {
        PnetDataApiLoginMethod loginMethod = getLoginMethod();

        if (loginMethod instanceof UsernamePasswordPnetDataApiLoginMethod)
        {
            return withLoginMethod(((UsernamePasswordPnetDataApiLoginMethod) loginMethod)
                .withUsernamePasswordSupplier(() -> new UsernamePasswordCredentials(username, password)));
        }

        if (loginMethod instanceof PnetDataApiTokenKey)
        {
            return withLoginMethod(((PnetDataApiTokenKey) loginMethod).withCredentials(username, password));
        }

        throw new UnsupportedOperationException(
            "This method is deprecated and does not support loginMethods of type " + loginMethod.getClass());
    }

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
