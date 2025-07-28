package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import pnet.data.api.PnetDataClientException;

/**
 * Repository for holding creating and caching RestCall objects.
 *
 * @author ham
 * @deprecated not needed anymore, since the {@link SimplePnetDataApiContext} is now doing the of caching calls.
 */
@Deprecated
@Service
public class PnetDataApiTokenRepository {

    private final Map<String, RestCall> restCalls = new HashMap<>();
    private final RestCallFactory factory;

    public PnetDataApiTokenRepository(RestCallFactory factory) {
        super();
        this.factory = factory;
    }

    public void invalidate(PnetDataApiLoginMethod loginMethod) {
        restCalls.remove(String.valueOf(loginMethod.hashCode()));
    }

    /**
     * Creates a rest call. Performs a login, if necessary. Caches the {@link RestCall} object.
     *
     * @param loginMethod the login method
     * @return the rest call, never null
     * @throws PnetDataClientException on occasion
     */
    public RestCall restCall(PnetDataApiLoginMethod loginMethod) throws PnetDataClientException {
        String key = String.valueOf(loginMethod.hashCode());
        RestCall restCall = restCalls.get(key);

        if (restCall == null) {
            restCall = loginMethod.performLogin(factory);

            cacheRestCall(key, restCall);
        }

        return restCall;
    }

    /**
     * Puts the rest call into the cache for later use
     *
     * @param key the key
     * @param restCall the rest call object
     */
    protected void cacheRestCall(String key, RestCall restCall) {
        restCalls.put(key, restCall);
    }
}
