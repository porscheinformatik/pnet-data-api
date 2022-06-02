package pnet.data.api.client.context;

import java.util.Objects;

/**
 * Username/Password for accessing the Partner.Net Data API
 *
 * @author ham
 */
public class UsernamePasswordCredentials
{
    private final String username;
    private final String password;

    public UsernamePasswordCredentials(String username, String password)
    {
        super();

        this.username = Objects.requireNonNull(username, "Username is null");
        this.password = Objects.requireNonNull(password, "Password is null");
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
        return Objects.hash(password, username);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof UsernamePasswordCredentials))
        {
            return false;
        }
        UsernamePasswordCredentials other = (UsernamePasswordCredentials) obj;
        return Objects.equals(password, other.password) && Objects.equals(username, other.username);
    }
}
