package pnet.data.api.client.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * Repository for holding creating and caching RestCall objects.
 *
 * @author ham
 */
@Service
public class PnetDataApiTokenRepository
{

    private static final String TOKEN_PREFIX = "Bearer";

    private final Map<PnetDataApiTokenKey, RestCall> restCalls = new HashMap<>();
    private final RestCallFactory factory;

    public PnetDataApiTokenRepository(RestCallFactory factory)
    {
        super();

        this.factory = factory;
    }

    public void invalidate(PnetDataApiTokenKey key)
    {
        restCalls.remove(key);
    }

    /**
     * Creates a rest call. Performs a login, if necessary. Caches the {@link RestCall} object.
     *
     * @param key the key
     * @return the rest call, never null
     * @throws PnetDataClientException on occasion
     */
    public RestCall restCall(PnetDataApiTokenKey key) throws PnetDataClientException
    {
        RestCall restCall = restCalls.get(key);

        if (restCall == null)
        {
            String token = performLogin(key);
            String url = key.getUrl();

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
    protected void cacheRestCall(PnetDataApiTokenKey key, RestCall restCall)
    {
        restCalls.put(key, restCall);
    }

    /**
     * Performs a login with the specified key and returns the token.
     *
     * @param key the key
     * @return the token, never null, never empty
     * @throws PnetDataClientException on any login error
     */
    protected String performLogin(PnetDataApiTokenKey key) throws PnetDataClientException
    {
        String url = key.getUrl();

        try
        {
            RestResponse<Void> response = factory.url(url).body(key).invoke(RestMethod.POST, "login", Void.class);

            if (response.isSuccessful())
            {
                String token = response.getFirstHeader("Authorization");

                if (PnetDataApiUtils.isEmpty(token))
                {
                    throw new PnetDataClientException("Authorization header is missing", url, key.getUsername(),
                        response);
                }

                if (token.startsWith(TOKEN_PREFIX))
                {
                    token = token.substring(TOKEN_PREFIX.length()).trim();
                }

                return token;
            }

            throw new PnetDataClientException("Login failed at \"%s\" with user \"%s\": %s", key.getUrl(),
                key.getUsername(), response);

        }
        catch (Exception e)
        {
            throw new PnetDataClientException("Login failed at \"%s\" with user \"%s\"", e, key.getUrl(),
                key.getUsername());
        }
    }

}
