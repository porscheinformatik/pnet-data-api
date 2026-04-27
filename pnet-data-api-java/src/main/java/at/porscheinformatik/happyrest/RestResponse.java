package at.porscheinformatik.happyrest;

import java.util.List;
import java.util.Locale;

/**
 * A response of a rest call.
 *
 * <p>
 * <strong>Security Considerations:</strong>
 * </p>
 * <ul>
 * <li><strong>Response Headers:</strong> Be cautious when logging or storing response headers, as they may contain
 * sensitive information (Set-Cookie, authorization tokens, etc.).</li>
 * <li><strong>Response Body:</strong> Response bodies may contain sensitive data (personal information, tokens,
 * credentials). Handle with appropriate security measures (encryption at rest, access controls).</li>
 * <li><strong>Error Details:</strong> Be careful not to log full response bodies in error cases, as they may contain
 * sensitive debugging information revealed by the server.</li>
 * </ul>
 *
 * @param <T> the type of the body
 * @author ham
 */
public interface RestResponse<T> {
    int getStatusCode();

    String getStatusMessage();

    default String getStatus() {
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
