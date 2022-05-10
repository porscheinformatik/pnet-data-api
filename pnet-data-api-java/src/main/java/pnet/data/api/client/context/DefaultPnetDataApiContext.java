package pnet.data.api.client.context;

/**
 * Default implementation of the {@link PnetDataApiContext}.
 *
 * @author ham
 */
public class DefaultPnetDataApiContext extends AbstractPnetDataApiContext
{
    private final PnetDataApiLoginMethod loginMethod;

    public DefaultPnetDataApiContext(PnetDataApiTokenRepository repository, PnetDataApiLoginMethod loginMethod)
    {
        super(repository);

        this.loginMethod = loginMethod;
    }

    @Override
    protected PnetDataApiLoginMethod getLoginMethod()
    {
        return loginMethod;
    }

    @Override
    @Deprecated
    public PnetDataApiContext withUrl(String url)
    {
        return new DefaultPnetDataApiContext(repository, loginMethod.withUrl(url));
    }

    @Override
    @Deprecated
    public PnetDataApiContext withCredentials(String username, String password)
    {
        return new DefaultPnetDataApiContext(repository, new UsernamePasswordPnetDataApiLoginMethod(
            loginMethod.getUrl(), () -> new UsernamePasswordCredentials(username, password)));
    }
}
