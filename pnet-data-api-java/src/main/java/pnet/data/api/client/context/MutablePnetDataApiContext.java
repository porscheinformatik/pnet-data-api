package pnet.data.api.client.context;

import pnet.data.api.PnetDataClientException;

/**
 * Default implementation of the {@link PnetDataApiContext}.
 *
 * @author ham
 */
public class MutablePnetDataApiContext extends AbstractPnetDataApiContext
{
    private PnetDataApiLoginMethod loginMethod = null;

    public MutablePnetDataApiContext(PnetDataApiTokenRepository repository)
    {
        super(repository);
    }

    @Override
    public PnetDataApiLoginMethod getLoginMethod()
    {
        if (loginMethod == null)
        {
            throw new IllegalStateException("LoginMethod not set");
        }

        return loginMethod;
    }

    public void setLoginMethod(PnetDataApiLoginMethod loginMethod)
    {
        try
        {
            invalidateLogin();
        }
        catch (PnetDataClientException e)
        {
            // ignore
        }

        this.loginMethod = loginMethod;
    }

    @Override
    @Deprecated
    public PnetDataApiContext withUrl(String url)
    {
        setLoginMethod(loginMethod.withUrl(url));

        return this;
    }

    @Override
    @Deprecated
    public PnetDataApiContext withCredentials(String username, String password)
    {
        setLoginMethod(new UsernamePasswordPnetDataApiLoginMethod(loginMethod.getUrl(),
            () -> new UsernamePasswordCredentials(username, password)));

        return this;
    }
}
