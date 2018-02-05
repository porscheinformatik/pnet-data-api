package pnet.data.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link PnetDataApiContext} that is based on the {@link PnetDataApiPrefs}.
 *
 * @author ham
 */
@Service
public class PrefsBasedPnetDataApiContext extends AbstractPnetDataApiContext
{

    private final PnetDataApiPrefs prefs;

    @Autowired
    public PrefsBasedPnetDataApiContext(PnetDataApiTokenRepository repository, PnetDataApiPrefs prefs)
    {
        super(repository);

        this.prefs = prefs;
    }

    @Override
    protected PnetDataApiTokenKey getKey()
    {
        return new PnetDataApiTokenKey(prefs.getPnetDataApiUrl(), prefs.getPnetDataApiTenant().getMatchcode(),
            prefs.getPnetDataApiUsername(), prefs.getPnetDataApiPassword());
    }

}
