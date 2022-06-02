package pnet.data.api.util;

import java.util.Objects;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import pnet.data.api.PnetDataClientException;
import pnet.data.api.client.context.AuthenticationTokenPnetDataApiLoginMethod;
import pnet.data.api.client.context.PnetDataApiLoginMethod;
import pnet.data.api.client.context.UsernamePasswordCredentials;
import pnet.data.api.client.context.UsernamePasswordPnetDataApiLoginMethod;

public class MutablePnetDataApiLoginMethod implements PnetDataApiLoginMethod
{
    public static MutablePnetDataApiLoginMethod createFromPrefs(String key)
    {
        MutablePnetDataApiLoginMethod loginMethod = new MutablePnetDataApiLoginMethod();
        String url = Prefs.getUrl(key);

        if (url != null)
        {
            loginMethod.setUrl(url);

            String username = Prefs.getUsername(key);
            String password = Prefs.getPassword(key);
            String token = Prefs.getToken(key);

            if (token != null)
            {
                loginMethod.setToken(token);
            }
            else if (username != null && password != null)
            {
                loginMethod.setUsernamePassword(username, password);
            }
        }

        return loginMethod;
    }

    public enum Type
    {
        USERNAME_PASSWORD,
        AUTHENTICATION_TOKEN
    }

    private String url;
    private Type type;
    private String username;
    private String password;
    private String token;

    private PnetDataApiLoginMethod loginMethod;

    public String getUrl()
    {
        return url;
    }

    public PnetDataApiLoginMethod withUrl(String url)
    {
        setUrl(url);

        return this;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Type getType()
    {
        return type;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsernamePassword(String username, String password)
    {
        type = Type.USERNAME_PASSWORD;

        this.username = username;
        this.password = password;

        token = null;

        loginMethod =
            new UsernamePasswordPnetDataApiLoginMethod(url, () -> new UsernamePasswordCredentials(username, password));
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        type = Type.AUTHENTICATION_TOKEN;

        username = null;
        password = null;

        this.token = token;

        loginMethod = new AuthenticationTokenPnetDataApiLoginMethod(url, () -> token);
    }

    @Override
    public RestCall performLogin(RestCallFactory factory) throws PnetDataClientException
    {
        if (loginMethod == null)
        {
            throw new IllegalStateException("LoginMethod not initialized, yet");
        }

        return loginMethod.performLogin(factory);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(password, token, type, url, username);
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
        if (getClass() != obj.getClass())
        {
            return false;
        }
        MutablePnetDataApiLoginMethod other = (MutablePnetDataApiLoginMethod) obj;
        return Objects.equals(password, other.password)
            && Objects.equals(token, other.token)
            && type == other.type
            && Objects.equals(url, other.url)
            && Objects.equals(username, other.username);
    }
}
