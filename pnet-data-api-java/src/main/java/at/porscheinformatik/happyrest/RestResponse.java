package at.porscheinformatik.happyrest;

import java.util.List;
import java.util.Locale;

/**
 * A response of a rest call.
 *
 * @author ham
 *
 * @param <T> the type of the body
 */
public interface RestResponse<T>
{

    int getStatusCode();

    String getStatusMessage();

    default String getStatus()
    {
        return getStatusCode() + " " + getStatusMessage();
    }

    boolean isInformational();

    boolean isSuccessful();

    boolean isRedirection();

    boolean isError();

    T getBody();

    List<String> getHeader(String key);

    String getFirstHeader(String key);

    String getCacheControl();

    MediaType getContentType();

    Locale getContentLanguage();

    long getContentLength();

    long getCreationDate();

    long getExpiresDate();

    long getLastModified();

}
