package pnet.data.api.client.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pnet.data.api.client.PnetDataClientPrefs;

/**
 * Implementation of the {@link PnetDataApiContext} that is based on the {@link PnetDataClientPrefs}.
 *
 * @author ham
 */
@Service
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
    protected PnetDataApiTokenKey getKey()
    {
        return new PnetDataApiTokenKey(prefs.getPnetDataApiUrl(), prefs.getPnetDataApiTenant(),
            prefs.getPnetDataApiUsername(), prefs.getPnetDataApiPassword());
    }

}
