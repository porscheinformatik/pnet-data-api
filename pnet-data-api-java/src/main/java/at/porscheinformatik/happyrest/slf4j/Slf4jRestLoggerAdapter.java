package at.porscheinformatik.happyrest.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestMethod;

/**
 * Logger adapter for SLF4J
 *
 * @author HAM
 *
 */
public class Slf4jRestLoggerAdapter implements RestLoggerAdapter
{

    private static Slf4jRestLoggerAdapter defaultLoggerAdapter = null;

    public static Slf4jRestLoggerAdapter getDefault()
    {
        Slf4jRestLoggerAdapter loggerAdapter = defaultLoggerAdapter;

        if (loggerAdapter == null)
        {
            loggerAdapter = new Slf4jRestLoggerAdapter();

            defaultLoggerAdapter = loggerAdapter;
        }

        return loggerAdapter;
    }

    private final Logger logger = LoggerFactory.getLogger(RestCall.class);

    @Override
    public void logRequest(RestMethod method, String uri)
    {
        logger.info("Sending {} request: {}", method, uri);
    }

    @Override
    public void warning(String message, Exception exception)
    {
        logger.warn(message, exception);
    }
}
