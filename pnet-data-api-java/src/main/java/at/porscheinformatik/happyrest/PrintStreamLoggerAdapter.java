package at.porscheinformatik.happyrest;

import java.io.PrintStream;

/**
 * A logger adapter for the System streams
 *
 * @author HAM
 */
public class PrintStreamLoggerAdapter implements RestLoggerAdapter
{

    private final PrintStream stream;

    public PrintStreamLoggerAdapter(PrintStream stream)
    {
        super();

        this.stream = stream;
    }

    @Override
    public void logRequest(RestMethod method, String uri)
    {
        stream.printf("Sending %s request: %s%n", method, uri);
    }

}
