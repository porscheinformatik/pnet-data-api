package at.porscheinformatik.happyrest;

import java.io.PrintStream;

/**
 * A logger adapter for the System streams
 *
 * @author HAM
 */
public class PrintStreamLoggerAdapter implements RestLoggerAdapter {

    private final PrintStream stream;

    public PrintStreamLoggerAdapter(PrintStream stream) {
        super();
        this.stream = stream;
    }

    @Override
    public void logRequest(RestMethod method, String uri) {
        stream.printf("%s %s%n", method, uri);
    }

    @Override
    public void warning(String message, Exception exception) {
        stream.println(message);

        if (exception != null) {
            exception.printStackTrace(stream);
        }
    }
}
