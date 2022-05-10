package pnet.data.api.client.context;

import org.springframework.beans.factory.annotation.Autowired;

import pnet.data.api.client.PnetDataClientPrefs;

/**
 * Implementation of the {@link PnetDataApiContext} that is based on the {@link PnetDataClientPrefs}.
 *
 * @author ham
 * @deprecated provide a {@link PnetDataApiLoginMethod} instead of this context
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
    protected PnetDataApiLoginMethod getLoginMethod()
    {
        return new PnetDataApiTokenKey(prefs.getPnetDataApiUrl(), prefs.getPnetDataApiUsername(),
            prefs.getPnetDataApiPassword());
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
