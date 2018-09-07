package pnet.data.api.util;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Preferences for storing things on the local machine
 *
 * @author ham
 */
public final class Prefs
{
    private static final Preferences PREFERENCES = Preferences.userNodeForPackage(Prefs.class);
    private static final SecretKey SECURITY_KEY = createBlowfishKey("wGo8e16IdulAQw8x+z5EAA==");
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public static final String DEFAULT_KEY = "default";

    private Prefs()
    {
        super();
    }

    public static SecretKey createBlowfishKey(String base64EncodedKey)
    {
        return createBlowfishKey(Base64.getDecoder().decode(base64EncodedKey));
    }

    public static SecretKey createBlowfishKey(byte[] key)
    {
        return new SecretKeySpec(key, "Blowfish");
    }

    public static String getUrl(String key)
    {
        return Prefs.get(key + ".url");
    }

    public static void setUrl(String key, String url)
    {
        Prefs.set(key + ".url", url);
    }

    public static String getUsername(String key)
    {
        return Prefs.get(key + ".username");
    }

    public static void setUsername(String key, String username)
    {
        Prefs.set(key + ".username", username);
    }

    public static String getPassword(String key)
    {
        return Prefs.decodeAndGet(key + ".password");
    }

    public static void setPassword(String key, String password)
    {
        Prefs.encodeAndSet(key + ".password", password);
    }

    public static void remove(String key)
    {
        PREFERENCES.remove(key + ".url");
        PREFERENCES.remove(key + ".username");
        PREFERENCES.remove(key + ".password");
    }

    public static List<String> keys()
    {
        ArrayList<String> result = new ArrayList<>();

        try
        {
            result.addAll(Arrays.asList(PREFERENCES.keys()));
        }
        catch (BackingStoreException e)
        {
            e.printStackTrace(System.err);
        }

        Collections.sort(result);

        return result;
    }

    protected static String get(String key)
    {
        return PREFERENCES.get(key, null);
    }

    protected static String decodeAndGet(String key)
    {
        return blowfishDecode(PREFERENCES.get(key, null));
    }

    protected static void set(String key, String value)
    {
        PREFERENCES.put(key, value);
    }

    protected static void encodeAndSet(String key, String value)
    {
        PREFERENCES.put(key, blowfishEncode(value));
    }

    protected static String blowfishEncode(String s)
    {
        return Base64.getEncoder().encodeToString(blowfishEncode(s.getBytes(CHARSET), SECURITY_KEY));
    }

    protected static String blowfishDecode(String base64EncodedBytes)
    {
        if (base64EncodedBytes == null || base64EncodedBytes.length() == 0)
        {
            return null;
        }

        try
        {
            return new String(blowfishDecode(Base64.getDecoder().decode(base64EncodedBytes), SECURITY_KEY));
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
            return "";
        }
    }

    private static byte[] blowfishEncode(byte[] bytes, SecretKey key)
    {
        Cipher cipher;

        try
        {
            cipher = Cipher.getInstance("Blowfish");
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException e)
        {
            throw new IllegalArgumentException("Hardcoded blowfishes do exist", e);
        }

        try
        {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }
        catch (InvalidKeyException e)
        {
            throw new IllegalArgumentException("Invalid key", e);
        }

        try
        {
            return cipher.doFinal(bytes);
        }
        catch (IllegalBlockSizeException | BadPaddingException e)
        {
            throw new IllegalArgumentException("Invalid input", e);
        }
    }

    private static byte[] blowfishDecode(byte[] bytes, SecretKey key)
    {
        if (bytes == null)
        {
            return null;
        }

        Cipher cipher;

        try
        {
            cipher = Cipher.getInstance("Blowfish");
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException e)
        {
            throw new IllegalArgumentException("Hardcoded blowfishes do exist", e);
        }

        try
        {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }
        catch (InvalidKeyException e)
        {
            throw new IllegalArgumentException("Invalid key", e);
        }

        try
        {
            return cipher.doFinal(bytes);
        }
        catch (IllegalBlockSizeException | BadPaddingException e)
        {
            throw new IllegalArgumentException("Invalid input", e);
        }
    }

}
