package at.porscheinformatik.happyrest;

import java.io.Serial;

/**
 * Base exception for REST call failures.
 *
 * <p>
 * This exception represents a general failure in REST call execution. It is the superclass for all REST-related
 * exceptions and can be caught to handle any REST error.
 * </p>
 *
 * <p>
 * <strong>Exception Hierarchy:</strong>
 * </p>
 * <ul>
 * <li><code>RestException</code> - Base exception (this class)</li>
 * <li><code>RestRequestException</code> - Errors during request building or execution (IOErrors, interruptions)
 * <ul>
 * <li><code>RestConnectionException</code> - Connection-level failures (no connection, DNS failure, etc.)</li>
 * <li><code>RestTimeoutException</code> - Request timeout (connection timeout, read timeout, etc.)</li>
 * </ul>
 * </li>
 * <li><code>RestResponseException</code> - HTTP error response (4xx, 5xx status codes)</li>
 * <li><code>RestFormatterException</code> - Request body formatting errors</li>
 * <li><code>RestParserException</code> - Response body parsing errors</li>
 * </ul>
 *
 * <p>
 * <strong>Handling Specific Error Types:</strong>
 * </p>
 *
 * <pre>
 * {@code
 * try {
 *     response = call.invoke(RestMethod.GET, String.class);
 * } catch (RestTimeoutException e) {
 *     // Handle timeout - retry with exponential backoff
 *     logger.warn("Request timed out after " + e.getTimeoutMillis() + "ms");
 * } catch (RestConnectionException e) {
 *     // Handle connection error - check network/server availability
 *     logger.error("Failed to connect: " + e.getMessage());
 * } catch (RestResponseException e) {
 *     // Handle HTTP error (4xx, 5xx)
 *     logger.error(e.getStatus() + ": " + e.getDescription());
 * } catch (RestException e) {
 *     // Handle any other REST error
 *     logger.error("Request failed: " + e.getMessage());
 * }
 * }
 * </pre>
 *
 * <p>
 * <strong>Backward Compatibility Note:</strong> Code that catches the superclass {@link RestException} will also
 * catch all subclass exceptions. New exception types like {@link RestConnectionException} and
 * {@link RestTimeoutException} are available for more granular error handling while maintaining compatibility with
 * existing error handling code.
 * </p>
 *
 * @author ham
 * @see RestRequestException
 * @see RestConnectionException
 * @see RestTimeoutException
 * @see RestResponseException
 * @see RestFormatterException
 * @see RestParserException
 */
public class RestException extends Exception {

    @Serial
    private static final long serialVersionUID = 6828148858084801707L;

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }
}
