package at.porscheinformatik.happyrest;

import java.io.Serial;

/**
 * Exception thrown when a request timeout occurs during a REST call.
 *
 * <p>
 * This exception is thrown when:
 * </p>
 * <ul>
 * <li>Connection timeout - server does not accept connection within the timeout period</li>
 * <li>Read timeout - server does not send response data within the timeout period</li>
 * <li>Write timeout - request body transmission times out</li>
 * <li>Socket timeout - any I/O operation times out</li>
 * </ul>
 *
 * <p>
 * <strong>Common Causes:</strong>
 * </p>
 * <ul>
 * <li>Server is overloaded or slow to respond</li>
 * <li>Network latency is high</li>
 * <li>Request is too large</li>
 * <li>Server is unresponsive</li>
 * </ul>
 *
 * <p>
 * <strong>Recovery Strategies:</strong>
 * </p>
 * <ul>
 * <li>Increase timeout duration (if appropriate)</li>
 * <li>Retry with exponential backoff</li>
 * <li>Check server responsiveness</li>
 * <li>Reduce request payload size</li>
 * <li>Check network connectivity and latency</li>
 * </ul>
 *
 * @author ham
 * @see RestConnectionException
 * @see RestRequestException
 */
public class RestTimeoutException extends RestException {

    @Serial
    private static final long serialVersionUID = 2L;

    private final long timeoutMillis;

    /**
     * Creates a new RestTimeoutException with the specified message.
     *
     * @param message the exception message
     */
    public RestTimeoutException(String message) {
        this(message, -1);
    }

    /**
     * Creates a new RestTimeoutException with the specified message and timeout duration.
     *
     * @param message         the exception message
     * @param timeoutMillis   the timeout duration in milliseconds (or -1 if unknown)
     */
    public RestTimeoutException(String message, long timeoutMillis) {
        super(message);
        this.timeoutMillis = timeoutMillis;
    }

    /**
     * Creates a new RestTimeoutException with the specified message and cause.
     *
     * @param message the exception message
     * @param cause   the underlying cause (typically a timeout exception from the HTTP client)
     */
    public RestTimeoutException(String message, Throwable cause) {
        this(message, -1, cause);
    }

    /**
     * Creates a new RestTimeoutException with the specified message, timeout duration, and cause.
     *
     * @param message         the exception message
     * @param timeoutMillis   the timeout duration in milliseconds (or -1 if unknown)
     * @param cause           the underlying cause (typically a timeout exception from the HTTP client)
     */
    public RestTimeoutException(String message, long timeoutMillis, Throwable cause) {
        super(message, cause);
        this.timeoutMillis = timeoutMillis;
    }

    /**
     * Gets the timeout duration that was exceeded, in milliseconds.
     *
     * @return the timeout duration in milliseconds, or -1 if unknown
     */
    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    /**
     * Checks if the timeout duration is known.
     *
     * @return true if the timeout duration is known (>= 0), false otherwise
     */
    public boolean isTimeoutKnown() {
        return timeoutMillis >= 0;
    }
}
