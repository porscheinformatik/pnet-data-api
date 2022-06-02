package pnet.data.api.client.context;

import org.springframework.beans.factory.annotation.Autowired;

import pnet.data.api.client.PnetDataClientPrefs;

/**
 * Implementation of the {@link PnetDataApiContext} that is based on the {@link PnetDataClientPrefs}.
 *
 * @author ham
 * @deprecated use the {@link SimplePnetDataApiContext} with an appropriate {@link PnetDataApiLoginMethod}, e.g.
 *             {@link AuthenticationTokenPnetDataApiLoginMethod} or {@link UsernamePasswordPnetDataApiLoginMethod}.
 */
@Deprecated
public class PrefsBasedPnetDataApiContext extends AbstractPnetDataApiContext
{
    private final PnetDataClientPrefs prefs;

    @Autowired
    public PrefsBasedPnetDataApiContext(PnetDataApiTokenRepository repository, PnetDataClientPrefs prefs)
    {
        super(repository);

        this.prefs = prefs;
    }

    @Override
    protected PnetDataApiTokenKey getLoginMethod()
    {
        return new PnetDataApiTokenKey(prefs.getPnetDataApiUrl(), prefs.getPnetDataApiUsername(),
            prefs.getPnetDataApiPassword());
    }

    @Override
    public PnetDataApiContext withLoginMethod(PnetDataApiLoginMethod loginMethod)
    {
        if (!(loginMethod instanceof PnetDataApiTokenKey))
        {
            throw new IllegalArgumentException("This deprecated context supports PnetDataApiTokenKey only");
        }
        return null;
    }

    @Override
    @Deprecated
    public PnetDataApiContext withUrl(String url)
    {
        return new DefaultPnetDataApiContext(repository, getLoginMethod().withUrl(url));
    }

    @Override
    @Deprecated
    public PnetDataApiContext withCredentials(String username, String password)
    {
        return new DefaultPnetDataApiContext(repository, new UsernamePasswordPnetDataApiLoginMethod(
            getLoginMethod().getUrl(), () -> new UsernamePasswordCredentials(username, password)));
    }
}
