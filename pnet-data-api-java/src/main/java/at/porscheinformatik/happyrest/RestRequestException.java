package at.porscheinformatik.happyrest;

import java.io.Serial;

/**
 * Exception thrown when a REST request fails during execution.
 *
 * <p>
 * This exception is thrown when:
 * </p>
 * <ul>
 * <li>The HTTP request cannot be built (invalid URL, etc.)</li>
 * <li>The request is interrupted (thread interruption)</li>
 * <li>Connection or I/O errors occur (wrapped from underlying HTTP client)</li>
 * <li>Other request execution failures</li>
 * </ul>
 *
 * <p>
 * <strong>Subclasses for Specific Errors:</strong>
 * </p>
 * <ul>
 * <li>{@link RestConnectionException} - For connection-specific failures</li>
 * <li>{@link RestTimeoutException} - For timeout-specific failures</li>
 * </ul>
 *
 * <p>
 * <strong>Note on Backward Compatibility:</strong> Current implementations may throw RestRequestException for
 * connection and timeout errors. For more granular error handling, catch the specific subclasses
 * ({@link RestConnectionException}, {@link RestTimeoutException}) after RestRequestException, or use cause chain
 * inspection via {@link #getCause()}.
 * </p>
 *
 * @author ham
 * @see RestConnectionException
 * @see RestTimeoutException
 */
public class RestRequestException extends RestException {

    @Serial
    private static final long serialVersionUID = -3314554757798478103L;

    public RestRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestRequestException(String message) {
        super(message);
    }
}
