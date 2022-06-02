package pnet.data.api.client.context;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.util.PnetDataApiUtils;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 * @deprecated replace the token with an appropriate {@link PnetDataApiLoginMethod}, e.g.
 *             {@link AuthenticationTokenPnetDataApiLoginMethod} or {@link UsernamePasswordPnetDataApiLoginMethod}.
 */
@Deprecated
public class PnetDataApiTokenKey implements PnetDataApiLoginMethod
{
    private static final String TOKEN_PREFIX = "Bearer";

    private final String url;
    private final String username;
    private final String password;

    public PnetDataApiTokenKey(String url, String username, String password)
    {
        super();

        this.url = Objects.requireNonNull(url, "Url is null");
        this.username = Objects.requireNonNull(username, "Username is null");
        this.password = Objects.requireNonNull(password, "Password is null");
    }

    @JsonIgnore
    public String getUrl()
    {
        return url;
    }

    public PnetDataApiTokenKey withUrl(String url)
    {
        return new PnetDataApiTokenKey(url, username, password);
    }

    public PnetDataApiTokenKey withCredentials(String username, String password)
    {
        return new PnetDataApiTokenKey(url, username, password);
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public RestCall performLogin(RestCallFactory factory) throws PnetDataClientException
    {
        String url = getUrl();

        try
        {
            RestCall restCall = factory.url(url);
            RestResponse<Void> response = restCall.body(this).path("login").invoke(RestMethod.POST, Void.class);

            if (response.isSuccessful())
            {
                String token = response.getFirstHeader("Authorization");

                if (PnetDataApiUtils.isEmpty(token))
                {
                    throw new PnetDataClientException("Authorization header is missing");
                }

                if (token.startsWith(TOKEN_PREFIX))
                {
                    token = token.substring(TOKEN_PREFIX.length()).trim();
                }

                return restCall.bearerAuthorization(token);
            }

            throw new PnetDataClientException("Login failed at \"%s\": %s", getUrl(), response);
        }
        catch (Exception e)
        {
            throw new PnetDataClientException("Login failed at \"%s\"", e, getUrl());
        }
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(password, url, username);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (!(obj instanceof PnetDataApiTokenKey))
        {
            return false;
        }

        PnetDataApiTokenKey other = (PnetDataApiTokenKey) obj;

        if (!Objects.equals(password, other.password))
        {
            return false;
        }

        if (!Objects.equals(url, other.url))
        {
            return false;
        }

        if (!Objects.equals(username, other.username))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("PnetDataApiClientTokenKey [url=%s, username=%s]", url, username);
    }
}
