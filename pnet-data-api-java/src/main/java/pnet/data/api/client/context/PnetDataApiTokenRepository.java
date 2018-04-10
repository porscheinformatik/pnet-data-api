package pnet.data.api.client.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import pnet.data.api.client.PnetDataClientLoginException;
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

    public RestCall createRestCall(PnetDataApiTokenKey key) throws PnetDataClientLoginException
    {
        RestCall restCall = restCalls.get(key);

        if (restCall == null)
        {
            restCall = login(key);
        }

        return restCall;
    }

    protected RestCall login(PnetDataApiTokenKey key) throws PnetDataClientLoginException
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
                    throw new PnetDataClientLoginException("Authorization header is missing", url, key.getUsername(),
                        response);
                }

                if (token.startsWith(TOKEN_PREFIX))
                {
                    token = token.substring(TOKEN_PREFIX.length()).trim();
                }

                RestCall restCall = factory.url(url).header("Authorization", TOKEN_PREFIX + " " + token);

                restCalls.put(key, restCall);

                return restCall;
            }

            throw new PnetDataClientLoginException("Login failed at \"%s\" with user \"%s\": %s", key.getUrl(),
                key.getUsername(), response);

        }
        catch (Exception e)
        {
            throw new PnetDataClientLoginException("Login failed at \"%s\" with user \"%s\"", e, key.getUrl(),
                key.getUsername(), null);
        }
    }

}
