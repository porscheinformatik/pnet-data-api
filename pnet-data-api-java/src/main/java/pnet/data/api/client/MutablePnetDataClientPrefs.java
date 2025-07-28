package pnet.data.api.client;

import pnet.data.api.client.context.PnetDataApiLoginMethod;

/**
 * Modifiable {@link PnetDataClientPrefs}.
 *
 * @author ham
 * @deprecated provide a {@link PnetDataApiLoginMethod} instead of this Prefs
 */
@Deprecated
public class MutablePnetDataClientPrefs implements PnetDataClientPrefs {

    private String url;
    private String username;
    private String password;

    public MutablePnetDataClientPrefs() {
        super();
    }

    public MutablePnetDataClientPrefs(String url, String username, String password) {
        super();
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getPnetDataApiUrl() {
        return url;
    }

    public void setPnetDataApiUrl(String url) {
        this.url = url;
    }

    @Override
    public String getPnetDataApiUsername() {
        return username;
    }

    public void setPnetDataApiUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPnetDataApiPassword() {
        return password;
    }

    public void setPnetDataApiPassword(String password) {
        this.password = password;
    }
}
