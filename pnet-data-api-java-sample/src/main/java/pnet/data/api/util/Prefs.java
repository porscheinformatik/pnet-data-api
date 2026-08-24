package pnet.data.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Preferences for storing things on the local machine
 *
 * @author ham
 */
public final class Prefs {

    private static final Preferences PREFERENCES = Preferences.userNodeForPackage(Prefs.class);

    public static final String DEFAULT_KEY = "default";

    private static final CipherHelper AES_CIPHER = CipherHelper.ofAesKey(
        "Y3p80YhT50y+QidV3ghpe0EWgal4XUKeNkWBwl3weII="
    );

    private Prefs() {}

    public static String getUrl(String key) {
        return Prefs.get(key + ".url");
    }

    public static void setUrl(String key, String url) {
        Prefs.set(key + ".url", url);
    }

    public static String getUsername(String key) {
        return Prefs.get(key + ".username");
    }

    public static void setUsername(String key, String username) {
        Prefs.set(key + ".username", username);
    }

    public static String getPassword(String key) {
        return Prefs.decodeAndGet(key + ".password");
    }

    public static void setPassword(String key, String password) {
        Prefs.encodeAndSet(key + ".password", password);
    }

    public static String getToken(String key) {
        return Prefs.decodeAndGet(key + ".token");
    }

    public static void setToken(String key, String token) {
        Prefs.encodeAndSet(key + ".token", token);
    }

    public static String getIdpUrl(String key) {
        return Prefs.get(key + ".idpUrl");
    }

    public static void setIdpUrl(String key, String idpUrl) {
        Prefs.set(key + ".idpUrl", idpUrl);
    }

    public static String getIdpClientId(String key) {
        return Prefs.get(key + ".idpClientId");
    }

    public static void setIdpClientId(String key, String clientId) {
        Prefs.set(key + ".idpClientId", clientId);
    }

    public static String getIdpClientSecret(String key) {
        return Prefs.decodeAndGet(key + ".idpClientSecret");
    }

    public static void setIdpClientSecret(String key, String clientSecret) {
        Prefs.encodeAndSet(key + ".idpClientSecret", clientSecret);
    }

    public static void remove(String key) {
        Prefs.PREFERENCES.remove(key + ".url");
        Prefs.PREFERENCES.remove(key + ".username");
        Prefs.PREFERENCES.remove(key + ".password");
        Prefs.PREFERENCES.remove(key + ".token");
        Prefs.PREFERENCES.remove(key + ".idpUrl");
        Prefs.PREFERENCES.remove(key + ".idpClientId");
        Prefs.PREFERENCES.remove(key + ".idpClientSecret");
    }

    public static List<String> keys() {
        ArrayList<String> result = new ArrayList<>();

        try {
            result.addAll(Arrays.asList(Prefs.PREFERENCES.keys()));
        } catch (BackingStoreException e) {
            e.printStackTrace(System.err);
        }

        Collections.sort(result);

        return result;
    }

    protected static String get(String key) {
        return Prefs.PREFERENCES.get(key, null);
    }

    protected static String decodeAndGet(String key) {
        String value = Prefs.PREFERENCES.get(key, null);

        if (value == null) {
            return null;
        }

        return Prefs.AES_CIPHER.decode(value);
    }

    protected static void set(String key, String value) {
        if (value == null || value.trim().isEmpty()) {
            Prefs.PREFERENCES.remove(key);
        } else {
            Prefs.PREFERENCES.put(key, value);
        }
    }

    protected static void encodeAndSet(String key, String value) {
        if (value == null || value.trim().isEmpty()) {
            Prefs.PREFERENCES.remove(key);
        } else {
            Prefs.PREFERENCES.put(key, Prefs.AES_CIPHER.encode(value));
        }
    }
}
