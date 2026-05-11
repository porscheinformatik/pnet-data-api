/**
 * Happy REST - A lightweight, multi-client REST abstraction layer for Java.
 *
 * <p>
 * This package provides a fluent, immutable REST client API supporting multiple HTTP implementations
 * (Java HttpClient, Apache HttpClient 4, Apache HttpClient 5, Spring RestTemplate, Spring WebClient)
 * without requiring compile-time dependencies on any specific HTTP client.
 * </p>
 *
 * <h2>Quick Start</h2>
 *
 * <pre>
 * {@code
 * RestCallFactory factory = JavaRestCallFactory.create(loggerAdapter, mapper);
 *
 * RestResponse<String> response = factory
 *     .url("https://api.example.com/users/123")
 *     .bearerAuthorization(token)
 *     .acceptJson()
 *     .invoke(RestMethod.GET, String.class);
 *
 * if (response.isSuccessful()) {
 *     System.out.println(response.getBody());
 * }
 * }
 * </pre>
 *
 * <h2>Security Recommendations</h2>
 *
 * <h3>1. Always Use HTTPS</h3>
 *
 * <p>
 * All API calls involving authentication or sensitive data MUST use HTTPS. HTTP is not secure for credential
 * transmission.
 * </p>
 *
 * <pre>
 * {@code
 * // ✅ Good
 * factory.url("https://api.example.com/resource")...
 *
 * // ❌ Bad - Do not use HTTP with credentials
 * factory.url("http://api.example.com/resource")...
 * }
 * </pre>
 *
 * <h3>2. Authentication Methods</h3>
 *
 * <p>
 * <strong>Recommended: Bearer Token (JWT, OAuth 2.0)</strong>
 * </p>
 *
 * <pre>
 * {@code
 * RestCall call = factory
 *     .url("https://api.example.com/data")
 *     .bearerAuthorization(jwtToken)
 *     .invoke(RestMethod.GET, Data.class);
 * }
 * </pre>
 *
 * <p>
 * <strong>⚠️ Avoid: Basic Authentication</strong>
 * </p>
 *
 * <p>
 * Basic Auth is discouraged because:
 * </p>
 * <ul>
 * <li>Credentials are Base64 encoded (easily decoded)</li>
 * <li>Credentials are transmitted with every request</li>
 * <li>Difficult to revoke or rotate credentials</li>
 * </ul>
 *
 * <p>
 * If you must use Basic Auth, ensure:
 * </p>
 * <ul>
 * <li>HTTPS is enforced (never use HTTP)</li>
 * <li>Credentials are not hardcoded in source</li>
 * <li>Credentials are loaded from secure storage (environment variables, vaults, key managers)</li>
 * </ul>
 *
 * <pre>
 * {@code
 * String username = System.getenv("API_USERNAME");  // ✅ Load from environment
 * String password = System.getenv("API_PASSWORD");  // ✅ Load from environment
 *
 * RestCall call = factory
 *     .url("https://api.example.com/data")  // HTTPS required
 *     .basicAuthorization(username, password)
 *     .invoke(RestMethod.GET, Data.class);
 * }
 * </pre>
 *
 * <h3>3. Sensitive Data in URLs</h3>
 *
 * <p>
 * Never include sensitive information (API keys, tokens, passwords) in URL query parameters or path segments.
 * These will appear in:
 * </p>
 * <ul>
 * <li>Server logs</li>
 * <li>Browser history</li>
 * <li>Proxy logs</li>
 * <li>Request logs (from this library)</li>
 * </ul>
 *
 * <pre>
 * {@code
 * // ❌ Bad - API key in query parameter (visible in logs)
 * factory.url("https://api.example.com/data?apiKey=secret123")...
 *
 * // ✅ Good - API key in Authorization header
 * factory.url("https://api.example.com/data")
 *     .bearerAuthorization("secret123")...
 * }
 * </pre>
 *
 * <h3>4. Logging Configuration</h3>
 *
 * <p>
 * This library logs requests via {@link at.porscheinformatik.happyrest.RestLoggerAdapter}. The logged information
 * includes:
 * </p>
 * <ul>
 * <li>HTTP method (GET, POST, etc.)</li>
 * <li>Request URL (including query parameters)</li>
 * </ul>
 *
 * <p>
 * <strong>Important:</strong> Ensure request logs are protected with appropriate access controls. If you have
 * sensitive data in URLs or headers, consider:
 * </p>
 * <ul>
 * <li>Implementing a custom {@link at.porscheinformatik.happyrest.RestLoggerAdapter} that masks sensitive data</li>
 * <li>Restricting log file access</li>
 * <li>Using log aggregation services with proper access controls</li>
 * </ul>
 *
 * <pre>
 * {@code
 * public class MaskedRestLoggerAdapter implements RestLoggerAdapter {
 *
 *     private static final List<String> SENSITIVE_KEYS =
 *         Arrays.asList("apiKey", "token", "password", "secret");
 *
 *     @Override
 *     public void logRequest(RestMethod method, String uri) {
 *         String maskedUri = maskSensitiveData(uri);
 *         System.out.println(method + " " + maskedUri);
 *     }
 *
 *     private String maskSensitiveData(String uri) {
 *         // Mask API keys, tokens, etc. in query parameters
 *         for (String key : SENSITIVE_KEYS) {
 *             uri = uri.replaceAll(key + "=[^&]*", key + "=***");
 *         }
 *         return uri;
 *     }
 *
 *     @Override
 *     public void warning(String message, Exception exception) {
 *         // Handle warnings with masked data
 *     }
 * }
 * }
 * </pre>
 *
 * <h3>5. Token Management</h3>
 *
 * <p>
 * If using bearer tokens (JWT, OAuth 2.0):
 * </p>
 * <ul>
 * <li><strong>Store Securely:</strong> Use secure credential storage, not environment variables or config files</li>
 * <li><strong>Refresh Before Expiry:</strong> Implement proper token refresh logic</li>
 * <li><strong>Use Short Expiration:</strong> Tokens should have short TTLs (e.g., 1 hour)</li>
 * <li><strong>Minimal Scope:</strong> Request tokens with only necessary permissions</li>
 * <li><strong>Revocation:</strong> Have a mechanism to revoke tokens if compromised</li>
 * </ul>
 *
 * <h3>6. Error Handling with Sensitive Data</h3>
 *
 * <p>
 * Be careful not to expose sensitive data in error messages or exception logs:
 * </p>
 *
 * <pre>
 * {@code
 * try {
 *     response.invoke(RestMethod.POST, String.class);
 * } catch (RestResponseException e) {
 *     // ✅ Log sanitized error message
 *     logger.error("API request failed with status " + e.getStatusCode());
 *
 *     // ❌ Don't log the full request (may contain credentials)
 *     // logger.error("Failed request: " + request);
 * }
 * }
 * </pre>
 *
 * <h2>Error Handling & Exception Types</h2>
 *
 * <p>
 * The Happy REST library provides a comprehensive exception hierarchy for fine-grained error handling:
 * </p>
 *
 * <pre>
 * {@code
 * try {
 *     response = factory
 *         .url("https://api.example.com/data")
 *         .bearerAuthorization(token)
 *         .invoke(RestMethod.GET, String.class);
 * } catch (RestTimeoutException e) {
 *     // Handle timeout - implement retry logic with exponential backoff
 *     logger.warn("Request timed out after " + e.getTimeoutMillis() + "ms, retrying...");
 *     // retry logic here
 * } catch (RestConnectionException e) {
 *     // Handle connection failure - check network/server availability
 *     logger.error("Cannot connect to server: " + e.getMessage());
 *     // alert operations
 * } catch (RestResponseException e) {
 *     // Handle HTTP error responses (4xx, 5xx)
 *     if (e.getStatusCode() == 401) {
 *         logger.error("Authentication failed - check credentials");
 *     } else {
 *         logger.error("Server returned " + e.getStatusCode() + ": " + e.getDescription());
 *     }
 * } catch (RestException e) {
 *     // Handle other REST errors (request building, parsing, etc.)
 *     logger.error("Request failed: " + e.getMessage());
 * }
 * }
 * </pre>
 *
 * <p>
 * <strong>Exception Hierarchy:</strong>
 * </p>
 * <ul>
 * <li>{@link at.porscheinformatik.happyrest.RestException} - Base exception for all REST errors
 * <ul>
 * <li>{@link at.porscheinformatik.happyrest.RestRequestException} - Request building/execution errors
 * <ul>
 * <li>{@link at.porscheinformatik.happyrest.RestConnectionException} - Connection failures (connection refused, DNS
 * failure, network unreachable, etc.)</li>
 * <li>{@link at.porscheinformatik.happyrest.RestTimeoutException} - Request timeout (connection timeout, read timeout,
 * socket timeout)</li>
 * </ul>
 * </li>
 * <li>{@link at.porscheinformatik.happyrest.RestResponseException} - HTTP error responses (4xx, 5xx status codes)
 * </li>
 * <li>{@link at.porscheinformatik.happyrest.RestFormatterException} - Request body formatting errors</li>
 * <li>{@link at.porscheinformatik.happyrest.RestParserException} - Response body parsing errors</li>
 * </ul>
 *
 * <p>
 * <strong>Backward Compatibility:</strong> Code catching the superclass {@link at.porscheinformatik.happyrest.RestException}
 * will also catch all subclass exceptions, ensuring existing error handling continues to work with new exception types.
 * </p>
 *
 * <h2>Thread Safety</h2>
 *
 * <p>
 * All {@link at.porscheinformatik.happyrest.RestCall} instances are immutable and thread-safe. The underlying HTTP
 * clients may or may not be thread-safe depending on implementation:
 * </p>
 * <ul>
 * <li><strong>JavaRestCallFactory (Java HttpClient):</strong> Thread-safe (HttpClient is thread-safe)</li>
 * <li><strong>Apache HttpClientFactory:</strong> Thread-safe (uses connection pooling)</li>
 * <li><strong>Apache5HttpClientFactory:</strong> Thread-safe (uses connection pooling)</li>
 * <li><strong>Spring RestTemplate/WebClient:</strong> Thread-safe when properly configured</li>
 * </ul>
 *
 * <h2>See Also</h2>
 *
 * <ul>
 * <li>{@link at.porscheinformatik.happyrest.RestCall} - Core REST call interface</li>
 * <li>{@link at.porscheinformatik.happyrest.RestCallFactory} - Factory for creating REST calls</li>
 * <li>{@link at.porscheinformatik.happyrest.RestResponse} - Response object</li>
 * <li>{@link at.porscheinformatik.happyrest.RestLoggerAdapter} - Logging configuration</li>
 * </ul>
 */
package at.porscheinformatik.happyrest;
