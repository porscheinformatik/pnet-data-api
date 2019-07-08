package at.porscheinformatik.happyrest;

/**
 * A logger adapter for the System streams
 *
 * @author HAM
 */
public class SystemRestLoggerAdapter implements RestLoggerAdapter
{

    public static final SystemRestLoggerAdapter INSTANCE = new SystemRestLoggerAdapter();

    @Override
    public void logRequest(RestMethod method, String uri)
    {
        System.out.printf("Sending %s request: %s\n", method, uri);
    }

}
