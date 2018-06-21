package pnet.data.api.client.context;

/**
 * Default implementation of the {@link PnetDataApiContext}.
 *
 * @author ham
 */
public class DefaultPnetDataApiContext extends AbstractPnetDataApiContext
{

    private final PnetDataApiTokenKey key;

    protected DefaultPnetDataApiContext(PnetDataApiTokenRepository repository, PnetDataApiTokenKey key)
    {
        super(repository);

        this.key = key;
    }

    @Override
    protected PnetDataApiTokenKey getKey()
    {
        return key;
    }

}
