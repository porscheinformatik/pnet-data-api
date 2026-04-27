package at.porscheinformatik.happyrest;

import java.io.Serial;

/**
 * Exception thrown when a connection error occurs during a REST call.
 *
 * <p>
 * This exception is thrown when:
 * </p>
 * <ul>
 * <li>The server is unreachable (connection refused, host unreachable, etc.)</li>
 * <li>DNS resolution fails</li>
 * <li>Network is unavailable</li>
 * <li>Connection cannot be established for other reasons</li>
 * </ul>
 *
 * <p>
 * <strong>Recovery Strategies:</strong>
 * </p>
 * <ul>
 * <li>Retry with exponential backoff</li>
 * <li>Check network connectivity</li>
 * <li>Verify server availability</li>
 * <li>Check DNS resolution</li>
 * </ul>
 *
 * @author ham
 * @see RestTimeoutException
 * @see RestRequestException
 */
public class RestConnectionException extends RestException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new RestConnectionException with the specified message.
     *
     * @param message the exception message
     */
    public RestConnectionException(String message) {
        super(message);
    }

    /**
     * Creates a new RestConnectionException with the specified message and cause.
     *
     * @param message the exception message
     * @param cause   the underlying cause (typically a connection error from the HTTP client)
     */
    public RestConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
