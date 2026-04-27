package at.porscheinformatik.happyrest;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import pnet.data.api.util.Pair;

/**
 * A REST call. Objects of this type must be final and thread-safe!
 *
 * <p>
 * <strong>Security Considerations:</strong>
 * </p>
 * <ul>
 * <li><strong>HTTPS Only:</strong> Always use HTTPS for all API calls, especially when transmitting sensitive data
 * (credentials, tokens, personal information).</li>
 * <li><strong>Sensitive Data in Logs:</strong> When request logging is enabled via {@link RestLoggerAdapter}, the
 * request URL and headers (including Authorization headers) will be logged. Ensure logs are protected and consider
 * masking sensitive data (API keys, tokens, passwords) in log output.</li>
 * <li><strong>Sensitive Data in URLs:</strong> Avoid placing sensitive information (API keys, tokens, passwords) in
 * query parameters, as they will appear in logs, browser history, and server logs.</li>
 * <li><strong>Authentication Methods:</strong> Use {@link #bearerAuthorization(String)} for token-based auth
 * (recommended), or {@link #basicAuthorization(String, String)} only for HTTPS connections. Prefer token-based
 * authentication over Basic Auth.</li>
 * </ul>
 *
 * @author ham
 */
public interface RestCall {
    String AUTHORIZATION_HEADER_NAME = "Authorization";

    String getUrl();

    /**
     * Returns a new instance of a {@link RestCall}, that points to the specified URL. The URL will be left unchanged
     * (no encoding will take place). Note: A URL must be set before invoking the request, otherwise a
     * NullPointerException will be thrown at invocation time.
     *
     * @param url the url (must be set to a non-null value before invoking)
     * @return a new instance
     * @throws NullPointerException if url is null when {@link #invoke(RestMethod, Class)} or
     *         {@link #invoke(RestMethod, GenericType)} is called
     */
    RestCall url(String url);

    /**
     * Returns a new instance of a {@link RestCall} and adds the path to the URL. The path may contain "/", "{" and "}",
     * which won't get encoded. The segments themselves will be encoded.
     *
     * @param path the path
     * @return a new instance
     */
    RestCall path(String path);

    /**
     * Returns a new instance of a {@link RestCall} and adds the path segments to the URL. The segments will get
     * encoded.
     *
     * @param pathSegments the segments to add
     * @return a new instance
     */
    RestCall pathSegment(String... pathSegments);

    /**
     * Returns a new instance of a {@link RestCall} and adds the path segments to the URL. The segments won't get
     * encoded.
     *
     * @param pathSegments the segments to add
     * @return a new instance
     */
    RestCall encodedPathSegment(String... pathSegments);

    /**
     * Gets the acceptable media types for the response.
     *
     * @return an immutable list of acceptable media types
     */
    List<MediaType> getAcceptableMediaTypes();

    /**
     * Sets the acceptable media type(s) for the response.
     *
     * <p>
     * The Accept header is used to tell the server which media types the client can handle in the response.
     * </p>
     *
     * @param mediaTypes the acceptable media type(s) (non-null)
     * @return a new RestCall instance with the accept type(s) set
     * @see #acceptJson()
     * @see #acceptXML()
     */
    RestCall accept(MediaType... mediaTypes);

    /**
     * Sets the accepted media type to JSON (application/json;charset=UTF-8).
     *
     * @return a new RestCall instance with JSON accept type
     * @see #accept(MediaType...)
     */
    default RestCall acceptJson() {
        return accept(MediaType.APPLICATION_JSON_UTF8);
    }

    /**
     * Sets the accepted media type to XML (application/xml).
     *
     * @return a new RestCall instance with XML accept type
     * @see #accept(MediaType...)
     */
    default RestCall acceptXML() {
        return accept(MediaType.APPLICATION_XML);
    }

    /**
     * Adds HTTP Basic Authentication header to the request.
     *
     * <p>
     * <strong>⚠️ SECURITY WARNING:</strong>
     * </p>
     * <ul>
     * <li><strong>HTTPS Required:</strong> Basic Auth transmits credentials in Base64 encoding (easily decoded).
     * ONLY use this over HTTPS connections. Never use over HTTP.</li>
     * <li><strong>Credentials in Memory:</strong> Passwords passed as plain String parameters may be visible in
     * memory dumps or logs. The password string is not automatically cleared after encoding.</li>
     * <li><strong>Credentials in Logs:</strong> If request logging is enabled, the Authorization header (including
     * encoded credentials) will be logged. Ensure logs are protected.</li>
     * <li><strong>Not Recommended for User Passwords:</strong> Avoid using with user-facing passwords. Consider
     * token-based authentication (e.g., {@link #bearerAuthorization(String)}) or OAuth 2.0 instead.</li>
     * </ul>
     *
     * <p>
     * <strong>Usage Example:</strong>
     * </p>
     *
     * <pre>
     * {@code
     * RestCall call = factory
     *     .url("https://api.example.com")  // HTTPS required
     *     .basicAuthorization("username", "password")
     *     .invoke(RestMethod.GET, String.class);
     * }
     * </pre>
     *
     * @param username the username (non-null)
     * @param password the password (non-null). Note: password is not cleared from memory after use.
     * @return a new RestCall instance with Basic Auth header added
     * @throws NullPointerException if username or password is null
     * @see #bearerAuthorization(String)
     */
    default RestCall basicAuthorization(String username, String password) {
        Charset charset = StandardCharsets.UTF_8;
        String credentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(charset));

        return header(RestCall.AUTHORIZATION_HEADER_NAME, "Basic " + credentials);
    }

    /**
     * Adds HTTP Bearer Token Authentication header to the request (recommended for API tokens and JWT).
     *
     * <p>
     * <strong>✅ Recommended:</strong> Bearer tokens are preferred over Basic Auth for API authentication.
     * </p>
     *
     * <p>
     * <strong>⚠️ SECURITY CONSIDERATIONS:</strong>
     * </p>
     * <ul>
     * <li><strong>HTTPS Required:</strong> Always use over HTTPS to prevent token interception.</li>
     * <li><strong>Token Storage:</strong> Tokens should be stored securely (secure storage, not in source code).</li>
     * <li><strong>Token in Logs:</strong> If request logging is enabled, the Authorization header will be logged.
     * Consider masking sensitive tokens in logs.</li>
     * <li><strong>Token Expiration:</strong> Ensure tokens are refreshed before expiration. Implement proper token
     * refresh logic in calling code.</li>
     * <li><strong>Token Scope:</strong> Use tokens with minimal required permissions (principle of least privilege).</li>
     * </ul>
     *
     * <p>
     * <strong>Usage Example with JWT:</strong>
     * </p>
     *
     * <pre>
     * {@code
     * String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";
     * RestCall call = factory
     *     .url("https://api.example.com")
     *     .bearerAuthorization(jwtToken)
     *     .invoke(RestMethod.GET, String.class);
     * }
     * </pre>
     *
     * @param token the bearer token (non-null, typically JWT or API token)
     * @return a new RestCall instance with Bearer Auth header added
     * @throws NullPointerException if token is null
     * @see #basicAuthorization(String, String)
     */
    default RestCall bearerAuthorization(String token) {
        return header(RestCall.AUTHORIZATION_HEADER_NAME, "Bearer " + token);
    }

    /**
     * Gets the Authorization header value.
     *
     * @return the Authorization header value, or null if not set
     * @see #basicAuthorization(String, String)
     * @see #bearerAuthorization(String)
     */
    default String getAuthorization() {
        return getHeader(RestCall.AUTHORIZATION_HEADER_NAME);
    }

    /**
     * Gets all attributes (headers, variables, parameters) added to this request.
     *
     * @return an immutable list of all attributes
     */
    List<RestAttribute> getAttributes();

    /**
     * Checks if any attributes of the specified type exist.
     *
     * @param type the attribute type (e.g., RestHeader.class, RestParameter.class)
     * @return true if at least one attribute of the specified type exists
     */
    default boolean containsAttributes(Class<? extends RestAttribute> type) {
        return getAttributes().stream().anyMatch(type::isInstance);
    }

    /**
     * Gets a single attribute value of the specified type and name.
     *
     * @param type the attribute type (e.g., RestHeader.class, RestParameter.class)
     * @param name the attribute name
     * @return an Optional containing the attribute value, or empty if not found
     */
    default Optional<Object> getAttribute(Class<? extends RestAttribute> type, String name) {
        return getAttributes()
            .stream()
            .filter(type::isInstance)
            .filter(attribute -> Objects.equals(attribute.getName(), name))
            .findFirst()
            .map(RestAttribute::getValue);
    }

    /**
     * Gets all attributes of the specified type and name.
     *
     * @param type the attribute type (e.g., RestHeader.class, RestParameter.class)
     * @param name the attribute name
     * @return an immutable list of all values for the attribute
     */
    default List<Object> getAttributes(Class<? extends RestAttribute> type, String name) {
        return getAttributes()
            .stream()
            .filter(type::isInstance)
            .filter(attribute -> Objects.equals(attribute.getName(), name))
            .map(RestAttribute::getValue)
            .toList();
    }

    /**
     * Gets all attributes of the specified type.
     *
     * @param <T> the attribute type
     * @param type the attribute class (e.g., RestHeader.class, RestParameter.class)
     * @return an immutable list of all attributes of the specified type
     */
    @SuppressWarnings("unchecked")
    default <T extends RestAttribute> List<T> getAttributes(Class<T> type) {
        return (List<T>) getAttributes().stream().filter(type::isInstance).toList();
    }

    /**
     * Sets the request body.
     *
     * <p>
     * The body will be formatted according to the current content type using the configured formatter. GET and
     * OPTIONS requests may not have a body; attempting to invoke with both a body and these methods will throw an
     * exception.
     * </p>
     *
     * @param body the request body (may be null). If non-null, it will be formatted to the content type.
     * @return a new RestCall instance with the body set
     * @throws RestRequestException if invoked with a GET or OPTIONS method that has a body
     */
    RestCall body(Object body);

    /**
     * Sets the request body content type (e.g., application/json, application/xml).
     *
     * <p>
     * The content type is used to format the request body when invoking the request.
     * </p>
     *
     * @param contentType the media type (non-null)
     * @return a new RestCall instance with the content type set
     * @see #body(Object)
     * @see #contentTypeJson()
     * @see #contentTypeXML()
     * @see #contentTypeForm()
     */
    RestCall contentType(MediaType contentType);

    /**
     * Sets the content type to JSON (application/json).
     *
     * @return a new RestCall instance with JSON content type
     * @see #contentType(MediaType)
     */
    default RestCall contentTypeJson() {
        return contentType(MediaType.APPLICATION_JSON);
    }

    /**
     * Sets the content type to XML (application/xml).
     *
     * @return a new RestCall instance with XML content type
     * @see #contentType(MediaType)
     */
    default RestCall contentTypeXML() {
        return contentType(MediaType.APPLICATION_XML);
    }

    /**
     * Sets the content type to form-encoded (application/x-www-form-urlencoded).
     *
     * <p>
     * When using form content type, add form parameters using {@link #parameter(String, Object...)} or
     * {@link #parameters(String, Collection)}. The parameters will be URL-encoded in the request body.
     * </p>
     *
     * @return a new RestCall instance with form content type
     * @see #contentType(MediaType)
     * @see #parameter(String, Object...)
     */
    default RestCall contentTypeForm() {
        return contentType(MediaType.APPLICATION_FORM);
    }

    /**
     * Adds one or more header values with the specified name.
     *
     * <p>
     * If a header with the same name already exists, the new values are appended (not replaced).
     * </p>
     *
     * @param name the header name (non-null)
     * @param value the header value(s) (non-null)
     * @return a new RestCall instance with the header(s) added
     * @see #replaceHeader(String, String...)
     * @see #removeHeaders(String...)
     */
    RestCall header(String name, String... value);

    /**
     * Gets the first value of the specified header.
     *
     * @param name the header name (non-null)
     * @return the first header value, or null if not set
     */
    default String getHeader(String name) {
        return (String) getAttribute(RestHeader.class, name).orElse(null);
    }

    /**
     * Adds multiple header values with the specified name from a collection.
     *
     * <p>
     * Equivalent to calling {@link #header(String, String...)} with multiple values.
     * </p>
     *
     * @param name the header name (non-null)
     * @param values the header values (non-null, non-empty)
     * @return a new RestCall instance with the header(s) added
     */
    RestCall headers(String name, Collection<String> values);

    /**
     * Replaces all headers with the specified name with new values.
     *
     * <p>
     * Any existing headers with the same name are removed before adding the new values.
     * </p>
     *
     * @param name the header name (non-null)
     * @param values the new header value(s) (non-null)
     * @return a new RestCall instance with the header(s) replaced
     * @see #header(String, String...)
     * @see #removeHeaders(String...)
     */
    RestCall replaceHeader(String name, String... values);

    /**
     * Replaces all headers with the specified name with values from a collection.
     *
     * @param name the header name (non-null)
     * @param values the new header values (non-null, non-empty)
     * @return a new RestCall instance with the header(s) replaced
     */
    RestCall replaceHeaders(String name, Collection<String> values);

    /**
     * Removes all headers with the specified names.
     *
     * @param names the header name(s) to remove (non-null)
     * @return a new RestCall instance with the header(s) removed
     */
    RestCall removeHeaders(String... names);

    /**
     * Checks if any headers have been added to this request.
     *
     * @return true if at least one header exists
     */
    default boolean containsHeaders() {
        return containsAttributes(RestHeader.class);
    }

    /**
     * Gets all values of a specific header name.
     *
     * @param name the header name (non-null)
     * @return an immutable list of all values for the header
     */
    default List<Object> getHeaders(String name) {
        return getAttributes(RestHeader.class, name);
    }

    /**
     * Gets all headers added to this request.
     *
     * @return an immutable list of all RestHeader attributes
     */
    default List<RestHeader> getHeaders() {
        return getAttributes(RestHeader.class);
    }

    /**
     * Adds a path variable to be substituted in the URL.
     *
     * <p>
     * Variables are substituted in the URL by matching placeholders in the form {variableName}. The variable value
     * is URL-encoded before substitution.
     * </p>
     *
     * <p>
     * <strong>Usage Example:</strong>
     * </p>
     *
     * <pre>
     * {@code
     * RestCall call = factory
     *     .url("https://api.example.com/users/{userId}/posts/{postId}")
     *     .variable("userId", 123)
     *     .variable("postId", 456)
     *     .invoke(RestMethod.GET, String.class);
     * // Resulting URL: https://api.example.com/users/123/posts/456
     * }
     * </pre>
     *
     * @param name the variable name (without braces, non-null)
     * @param value the variable value (non-null, will be formatted and URL-encoded)
     * @return a new RestCall instance with the variable added
     */
    RestCall variable(String name, Object value);

    /**
     * Checks if any path variables have been added to this request.
     *
     * @return true if at least one variable exists
     */
    default boolean containsVariables() {
        return containsAttributes(RestVariable.class);
    }

    /**
     * Gets the value of a specific path variable.
     *
     * @param name the variable name (non-null)
     * @return the variable value, or null if not set
     */
    default Object getVariable(String name) {
        return getAttribute(RestVariable.class, name).orElse(null);
    }

    /**
     * Gets all path variables added to this request.
     *
     * @return an immutable list of all RestVariable attributes
     */
    default List<RestVariable> getVariables() {
        return getAttributes(RestVariable.class);
    }

    /**
     * Adds one or more query parameters with the specified name.
     *
     * <p>
     * Parameters are appended to the query string in the URL. Multiple values for the same parameter name are
     * supported and will be appended as separate key=value pairs.
     * </p>
     *
     * <p>
     * <strong>Usage Example:</strong>
     * </p>
     *
     * <pre>
     * {@code
     * RestCall call = factory
     *     .url("https://api.example.com/search")
     *     .parameter("q", "java")
     *     .parameter("page", 1)
     *     .parameter("tags", "spring", "boot")  // Multiple values
     *     .invoke(RestMethod.GET, String.class);
     * // Resulting URL: https://api.example.com/search?q=java&page=1&tags=spring&tags=boot
     * }
     * </pre>
     *
     * @param name the parameter name (non-null)
     * @param value the parameter value(s) (non-null)
     * @return a new RestCall instance with the parameter(s) added
     * @see #parameters(String, Collection)
     * @see #replaceParameter(String, String...)
     */
    RestCall parameter(String name, Object... value);

    /**
     * Gets the first value of a specific query parameter.
     *
     * @param name the parameter name (non-null)
     * @return the parameter value, or null if not set
     */
    default Object getParameter(String name) {
        return getAttribute(RestParameter.class, name).orElse(null);
    }

    /**
     * Adds multiple query parameter values from a collection.
     *
     * @param name the parameter name (non-null)
     * @param values the parameter values (non-null, non-empty)
     * @return a new RestCall instance with the parameter(s) added
     */
    RestCall parameters(String name, Collection<?> values);

    /**
     * Adds multiple query parameters from key-value pairs.
     *
     * @param values a collection of name-value pairs (non-null, non-empty)
     * @return a new RestCall instance with the parameters added
     */
    RestCall parameters(Collection<? extends Pair<String, ?>> values);

    /**
     * Replaces all parameters with the specified name with new values.
     *
     * @param name the parameter name (non-null)
     * @param values the new parameter value(s) (non-null)
     * @return a new RestCall instance with the parameter(s) replaced
     */
    RestCall replaceParameter(String name, String... values);

    /**
     * Replaces all parameters with the specified name with values from a collection.
     *
     * @param name the parameter name (non-null)
     * @param values the new parameter values (non-null, non-empty)
     * @return a new RestCall instance with the parameter(s) replaced
     */
    RestCall replaceParameters(String name, Collection<String> values);

    /**
     * Replaces multiple parameters from key-value pairs.
     *
     * @param values a collection of name-value pairs (non-null, non-empty)
     * @return a new RestCall instance with the parameters replaced
     */
    RestCall replaceParameters(Collection<? extends Pair<String, ?>> values);

    /**
     * Removes all parameters with the specified names.
     *
     * @param names the parameter name(s) to remove (non-null)
     * @return a new RestCall instance with the parameter(s) removed
     */
    RestCall removeParameters(String... names);

    /**
     * Checks if any query parameters have been added to this request.
     *
     * @return true if at least one parameter exists
     */
    default boolean containsParameters() {
        return containsAttributes(RestParameter.class);
    }

    /**
     * Gets all values of a specific query parameter name.
     *
     * @param name the parameter name (non-null)
     * @return an immutable list of all values for the parameter
     */
    default List<Object> getParameters(String name) {
        return getAttributes(RestParameter.class, name);
    }

    /**
     * Gets all query parameters added to this request.
     *
     * @return an immutable list of all RestParameter attributes
     */
    default List<RestParameter> getParameters() {
        return getAttributes(RestParameter.class);
    }

    /**
     * Invokes a GET request and returns the response body directly.
     *
     * @param <T> the response body type
     * @param responseType the response body type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, Class)
     */
    default <T> T get(Class<T> responseType) throws RestException {
        return invoke(RestMethod.GET, responseType).getBody();
    }

    /**
     * Invokes a GET request and returns the response body directly (supports generic types).
     *
     * @param <T> the response body type
     * @param responseType the response body generic type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, GenericType)
     */
    default <T> T get(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.GET, responseType).getBody();
    }

    /**
     * Invokes a PUT request and returns the response body directly.
     *
     * @param <T> the response body type
     * @param responseType the response body type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, Class)
     */
    default <T> T put(Class<T> responseType) throws RestException {
        return invoke(RestMethod.PUT, responseType).getBody();
    }

    /**
     * Invokes a PUT request and returns the response body directly (supports generic types).
     *
     * @param <T> the response body type
     * @param responseType the response body generic type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, GenericType)
     */
    default <T> T put(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.PUT, responseType).getBody();
    }

    /**
     * Invokes a POST request and returns the response body directly.
     *
     * @param <T> the response body type
     * @param responseType the response body type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, Class)
     */
    default <T> T post(Class<T> responseType) throws RestException {
        return invoke(RestMethod.POST, responseType).getBody();
    }

    /**
     * Invokes a POST request and returns the response body directly (supports generic types).
     *
     * @param <T> the response body type
     * @param responseType the response body generic type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, GenericType)
     */
    default <T> T post(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.POST, responseType).getBody();
    }

    /**
     * Invokes a DELETE request and returns the response body directly.
     *
     * @param <T> the response body type
     * @param responseType the response body type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, Class)
     */
    default <T> T delete(Class<T> responseType) throws RestException {
        return invoke(RestMethod.DELETE, responseType).getBody();
    }

    /**
     * Invokes a DELETE request and returns the response body directly (supports generic types).
     *
     * @param <T> the response body type
     * @param responseType the response body generic type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, GenericType)
     */
    default <T> T delete(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.DELETE, responseType).getBody();
    }

    /**
     * Invokes an OPTIONS request and returns the response body directly.
     *
     * @param <T> the response body type
     * @param responseType the response body type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, Class)
     */
    default <T> T options(Class<T> responseType) throws RestException {
        return invoke(RestMethod.OPTIONS, responseType).getBody();
    }

    /**
     * Invokes an OPTIONS request and returns the response body directly (supports generic types).
     *
     * @param <T> the response body type
     * @param responseType the response body generic type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, GenericType)
     */
    default <T> T options(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.OPTIONS, responseType).getBody();
    }

    /**
     * Invokes a PATCH request and returns the response body directly.
     *
     * @param <T> the response body type
     * @param responseType the response body type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, Class)
     */
    default <T> T patch(Class<T> responseType) throws RestException {
        return invoke(RestMethod.PATCH, responseType).getBody();
    }

    /**
     * Invokes a PATCH request and returns the response body directly (supports generic types).
     *
     * @param <T> the response body type
     * @param responseType the response body generic type (non-null)
     * @return the response body
     * @throws RestException if the request fails or returns an error status
     * @see #invoke(RestMethod, GenericType)
     */
    default <T> T patch(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.PATCH, responseType).getBody();
    }

    /**
     * Invokes the HTTP request with the specified method and returns the full response.
     *
     * <p>
     * This method sends the request and returns both the response status and body. Use this when you need to inspect
     * the response headers or status code.
     * </p>
     *
     * <p>
     * <strong>URL Requirement:</strong> A URL must be set before invoking, otherwise a NullPointerException will be
     * thrown with message "URL must be set before invoking a request".
     * </p>
     *
     * @param <T> the response body type
     * @param method the HTTP method (GET, POST, PUT, DELETE, PATCH, OPTIONS) (non-null)
     * @param responseType the expected response body type (non-null)
     * @return the RestResponse containing status code, headers, and body
     * @throws RestException if the request fails or returns an error status
     * @throws NullPointerException if URL is not set before invocation
     */
    <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException;

    /**
     * Invokes the HTTP request with the specified method and returns the full response (supports generic types).
     *
     * <p>
     * Use this method when the response type is a generic type (e.g., List&lt;String&gt;, Map&lt;String, User&gt;).
     * </p>
     *
     * <p>
     * <strong>URL Requirement:</strong> A URL must be set before invoking, otherwise a NullPointerException will be
     * thrown with message "URL must be set before invoking a request".
     * </p>
     *
     * <p>
     * <strong>Usage Example:</strong>
     * </p>
     *
     * <pre>
     * {@code
     * GenericType<List<User>> responseType = GenericType.build(List.class).with(User.class);
     * RestResponse<List<User>> response = factory
     *     .url("https://api.example.com/users")
     *     .invoke(RestMethod.GET, responseType);
     * List<User> users = response.getBody();
     * }
     * </pre>
     *
     * @param <T> the response body type
     * @param method the HTTP method (GET, POST, PUT, DELETE, PATCH, OPTIONS) (non-null)
     * @param responseType the expected response body generic type (non-null)
     * @return the RestResponse containing status code, headers, and body
     * @throws RestException if the request fails or returns an error status
     * @throws NullPointerException if URL is not set before invocation
     */
    <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException;
}
