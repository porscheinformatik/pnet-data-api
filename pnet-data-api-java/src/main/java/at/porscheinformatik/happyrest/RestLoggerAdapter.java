package at.porscheinformatik.happyrest;

/**
 * Used for logging
 *
 * @author HAM
 */
public interface RestLoggerAdapter {
    void logRequest(RestMethod method, String uri);

    void warning(String message, Exception exception);
}
