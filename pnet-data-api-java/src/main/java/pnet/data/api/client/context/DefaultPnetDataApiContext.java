package pnet.data.api.client.context;

/**
 * Default implementation of the {@link PnetDataApiContext}.
 *
 * @author ham
 * @deprecated use the {@link SimplePnetDataApiContext} instead. It does not need a repository.
 */
@Deprecated
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
    public PnetDataApiContext withLoginMethod(PnetDataApiLoginMethod loginMethod)
    {
        return new DefaultPnetDataApiContext(repository, loginMethod);
    }
}
