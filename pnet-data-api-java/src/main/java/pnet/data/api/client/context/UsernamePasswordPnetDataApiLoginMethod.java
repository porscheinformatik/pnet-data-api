package pnet.data.api.client.context;

import java.util.function.Supplier;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestMethod;
import at.porscheinformatik.happyrest.RestResponse;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.util.PnetDataApiUtils;

public class UsernamePasswordPnetDataApiLoginMethod implements PnetDataApiLoginMethod
{
    private static final String TOKEN_PREFIX = "Bearer";

    private final String url;
    private final String key;
    private final Supplier<UsernamePasswordCredentials> usernamePasswordSupplier;

    public UsernamePasswordPnetDataApiLoginMethod(String url,
        Supplier<UsernamePasswordCredentials> usernamePasswordSupplier)
    {
        super();
        this.url = url;
        this.usernamePasswordSupplier = usernamePasswordSupplier;

        UsernamePasswordCredentials usernamePassword = usernamePasswordSupplier.get();

        key = PnetDataApiUtils.checksum(url + usernamePassword.getUsername() + usernamePassword.getPassword());
    }

    @Override
    public String getUrl()
    {
        return url;
    }

    @Override
    public PnetDataApiLoginMethod withUrl(String url)
    {
        return new UsernamePasswordPnetDataApiLoginMethod(url, usernamePasswordSupplier);
    }

    @Override
    public String getKey()
    {
        return key;
    }

    @Override
    public String performLogin(RestCallFactory factory) throws PnetDataClientException
    {
        String url = getUrl();

        try
        {
            RestResponse<Void> response =
                factory.url(url).body(usernamePasswordSupplier.get()).path("login").invoke(RestMethod.POST, Void.class);

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

                return token;
            }

            throw new PnetDataClientException("Login failed at \"%s\": %s", getUrl(), response);
        }
        catch (Exception e)
        {
            throw new PnetDataClientException("Login failed at \"%s\"", e, getUrl());
        }
    }
}
