package at.porscheinformatik.happyrest;

/**
 * Adapter for logging REST calls. Implementations handle request and error logging.
 *
 * <p>
 * <strong>⚠️ SECURITY WARNING:</strong>
 * </p>
 * <p>
 * The logged URI may contain sensitive information:
 * </p>
 * <ul>
 * <li>Query parameters with API keys or tokens</li>
 * <li>Path parameters with sensitive IDs</li>
 * <li>Authentication tokens in custom headers</li>
 * </ul>
 *
 * <p>
 * <strong>Recommendations:</strong>
 * </p>
 * <ul>
 * <li>Implement custom {@link RestLoggerAdapter} to mask sensitive data in URIs</li>
 * <li>Restrict access to log files containing API requests</li>
 * <li>Use log aggregation services with appropriate access controls</li>
 * <li>Never log Authorization headers or request bodies</li>
 * <li>Sanitize URIs before logging (remove API keys, tokens, passwords)</li>
 * </ul>
 *
 * <p>
 * <strong>Example: Secure Logger Implementation</strong>
 * </p>
 *
 * <pre>
 * {@code
 * public class SecureRestLoggerAdapter implements RestLoggerAdapter {
 *
 *     private static final Pattern API_KEY_PATTERN =
 *         Pattern.compile("(apiKey|api_key|token|key)=([^&]+)", Pattern.CASE_INSENSITIVE);
 *
 *     private final Logger logger;
 *
 *     public SecureRestLoggerAdapter(Logger logger) {
 *         this.logger = logger;
 *     }
 *
 *     @Override
 *     public void logRequest(RestMethod method, String uri) {
 *         String maskedUri = maskSensitiveData(uri);
 *         logger.info(method + " " + maskedUri);
 *     }
 *
 *     private String maskSensitiveData(String uri) {
 *         // Replace sensitive query parameter values with ***
 *         return API_KEY_PATTERN.matcher(uri)
 *             .replaceAll("$1=***");
 *     }
 *
 *     @Override
 *     public void warning(String message, Exception exception) {
 *         // Log warnings without exposing sensitive data
 *         logger.warn(message, exception);
 *     }
 * }
 * }
 * </pre>
 *
 * @author HAM
 */
public interface RestLoggerAdapter {
    /**
     * Log a REST API request.
     *
     * <p>
     * <strong>⚠️ Security Note:</strong> The URI parameter may contain sensitive information (API keys, tokens,
     * personal data in query parameters). Consider masking sensitive data before logging.
     * </p>
     *
     * @param method the HTTP method (GET, POST, etc.)
     * @param uri    the request URI (may contain query parameters)
     */
    void logRequest(RestMethod method, String uri);

    /**
     * Log a warning or error during request processing.
     *
     * <p>
     * <strong>⚠️ Security Note:</strong> Exception messages and stack traces should not contain sensitive data
     * (passwords, tokens, personal information).
     * </p>
     *
     * @param message   the warning message
     * @param exception the exception that occurred (may be null)
     */
    void warning(String message, Exception exception);
}
