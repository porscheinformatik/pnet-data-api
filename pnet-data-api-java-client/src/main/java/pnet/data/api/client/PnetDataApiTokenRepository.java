package pnet.data.api.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.spring.SpringRestCall;

/**
 * Repository for holding creating and caching RestCall objects.
 *
 * @author ham
 */
@Service
public class PnetDataApiTokenRepository
{

    private final Map<PnetDataApiTokenKey, RestCall> restCalls = new HashMap<>();

    public PnetDataApiTokenRepository()
    {
        super();
    }

    public void invalidate(PnetDataApiTokenKey key)
    {
        restCalls.remove(key);
    }

    public RestCall createRestCall(PnetDataApiTokenKey key) throws PnetDataApiLoginException
    {
        RestCall restCall = restCalls.get(key);

        if (restCall == null)
        {
            restCall = login(key);
        }

        return restCall;
    }

    protected RestCall login(PnetDataApiTokenKey key) throws PnetDataApiLoginException
    {
        String url = key.getUrl();

        try
        {
            RestResponse<Void> response =
                new SpringRestCall().url(url).acceptJson().body(key).invoke(RestMethod.POST, "login", Void.class);

            if (response.isSuccessful())
            {
                String token = response.getFirstHeader("Authorization");
                RestCall restCall = new SpringRestCall(url).header("Authorization", token);

                restCalls.put(key, restCall);

                return restCall;
            }

            throw new PnetDataApiLoginException("Login failed at \"%s\" with user \"%s\": %s", key.getUrl(),
                key.getUsername(), response);

        }
        catch (Exception e)
        {
            throw new PnetDataApiLoginException("Login failed at \"%s\" with user \"%s\"", e, key.getUrl(),
                key.getUsername(), null);
        }
    }

}
