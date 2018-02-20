package pnet.data.api.client.context;

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
    private final String tenant;
    private final String username;
    private final String password;

    public PnetDataApiTokenKey(String url, String tenant, String username, String password)
    {
        super();

        this.url = Objects.requireNonNull(url, "Url is null");
        this.tenant = Objects.requireNonNull(tenant, "Tenant is null");
        this.username = Objects.requireNonNull(username, "Username is null");
        this.password = Objects.requireNonNull(password, "Password is null");
    }

    @JsonIgnore
    public String getUrl()
    {
        return url;
    }

    public String getTenant()
    {
        return tenant;
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
        result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());
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

        if (tenant == null)
        {
            if (other.tenant != null)
            {
                return false;
            }
        }
        else if (!tenant.equals(other.tenant))
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
        return String.format("PnetDataApiClientTokenKey [url=%s, tenant=%s, username=%s]", url, tenant, username);
    }

}
