package pnet.data.api.client;

/**
 * Modifiable {@link PnetDataClientPrefs}.
 *
 * @author ham
 */
public class MutablePnetDataClientPrefs implements PnetDataClientPrefs
{

    private String url;
    private String username;
    private String password;
    private String tenant;

    public MutablePnetDataClientPrefs()
    {
        super();
    }

    public MutablePnetDataClientPrefs(String url, String username, String password, String tenant)
    {
        super();
        this.url = url;
        this.username = username;
        this.password = password;
        this.tenant = tenant;
    }

    @Override
    public String getPnetDataApiUrl()
    {
        return url;
    }

    public void setPnetDataApiUrl(String url)
    {
        this.url = url;
    }

    @Override
    public String getPnetDataApiUsername()
    {
        return username;
    }

    public void setPnetDataApiUsername(String username)
    {
        this.username = username;
    }

    @Override
    public String getPnetDataApiPassword()
    {
        return password;
    }

    public void setPnetDataApiPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String getPnetDataApiTenant()
    {
        return tenant;
    }

    public void setPnetDataApiTenant(String tenant)
    {
        this.tenant = tenant;
    }

}
