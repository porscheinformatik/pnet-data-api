package pnet.data.api.client;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Context for accessing the Partner.Net Data API
 *
 * @author ham
 */
public class PnetDataApiTokenKey
{

    private final String url;
    private final String pnId;
    private final String username;
    private final String password;

    public PnetDataApiTokenKey(String url, String pnId, String username, String password)
    {
        super();

        this.url = Objects.requireNonNull(url, "Url is null");
        this.pnId = Objects.requireNonNull(pnId, "PnId is null");
        this.username = Objects.requireNonNull(username, "Username is null");
        this.password = Objects.requireNonNull(password, "Password is null");
    }

    @JsonIgnore
    public String getUrl()
    {
        return url;
    }

    public String getPnId()
    {
        return pnId;
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
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((pnId == null) ? 0 : pnId.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());

        return result;
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

        if (password == null)
        {
            if (other.password != null)
            {
                return false;
            }
        }
        else if (!password.equals(other.password))
        {
            return false;
        }

        if (pnId == null)
        {
            if (other.pnId != null)
            {
                return false;
            }
        }
        else if (!pnId.equals(other.pnId))
        {
            return false;
        }

        if (url == null)
        {
            if (other.url != null)
            {
                return false;
            }
        }
        else if (!url.equals(other.url))
        {
            return false;
        }

        if (username == null)
        {
            if (other.username != null)
            {
                return false;
            }
        }
        else if (!username.equals(other.username))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format("PnetDataApiClientTokenKey [url=%s, pnId=%s, username=%s]", url, pnId, username);
    }

}
