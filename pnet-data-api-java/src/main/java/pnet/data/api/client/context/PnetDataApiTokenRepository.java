package pnet.data.api.client.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import pnet.data.api.PnetDataClientException;

/**
 * Repository for holding creating and caching RestCall objects.
 *
 * @author ham
 */
@Service
public class PnetDataApiTokenRepository
{
    private final Map<String, RestCall> restCalls = new HashMap<>();
    private final RestCallFactory factory;

    public PnetDataApiTokenRepository(RestCallFactory factory)
    {
        super();

        this.factory = factory;
    }

    public void invalidate(PnetDataApiLoginMethod loginMethod)
    {
        restCalls.remove(loginMethod.getKey());
    }

    /**
     * Creates a rest call. Performs a login, if necessary. Caches the {@link RestCall} object.
     *
     * @param loginMethod the login method
     * @return the rest call, never null
     * @throws PnetDataClientException on occasion
     */
    public RestCall restCall(PnetDataApiLoginMethod loginMethod) throws PnetDataClientException
    {
        String key = loginMethod.getKey();
        RestCall restCall = restCalls.get(key);

        if (restCall == null)
        {
            String token = loginMethod.performLogin(factory);
            String url = loginMethod.getUrl();

            restCall = createRestCall(url, token);

            cacheRestCall(key, restCall);
        }

        return restCall;
    }

    /**
     * Creates a new rest call with the specified url and the specified token.
     *
     * @param url the url
     * @param token the token
     * @return the rest call
     */
    protected RestCall createRestCall(String url, String token)
    {
        return factory.url(url).bearerAuthorization(token);
    }

    /**
     * Puts the rest call into the cache for later use
     *
     * @param key the key
     * @param restCall the rest call object
     */
    protected void cacheRestCall(String key, RestCall restCall)
    {
        restCalls.put(key, restCall);
    }
}
