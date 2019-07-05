package at.porscheinformatik.happyrest;

/**
 * Used for logging
 *
 * @author HAM
 */
public interface RestLoggerAdapter
{

    void logRequest(RestMethod method, String uri);

}
