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
 * @author ham
 */
public interface RestCall {
    String AUTHORIZATION_HEADER_NAME = "Authorization";

    String getUrl();

    /**
     * Returns a new instance of a {@link RestCall}, that points to the specified URL. The URL will be left unchanged
     * (no encoding will take place)
     *
     * @param url the url
     * @return a new instance
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

    List<MediaType> getAcceptableMediaTypes();

    RestCall accept(MediaType... mediaTypes);

    default RestCall acceptJson() {
        return accept(MediaType.APPLICATION_JSON_UTF8);
    }

    default RestCall acceptXML() {
        return accept(MediaType.APPLICATION_XML);
    }

    default RestCall basicAuthorization(String username, String password) {
        Charset charset = StandardCharsets.UTF_8;
        String credentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(charset));

        return header(AUTHORIZATION_HEADER_NAME, "Basic " + credentials);
    }

    default RestCall bearerAuthorization(String token) {
        return header(AUTHORIZATION_HEADER_NAME, "Bearer " + token);
    }

    default String getAuthorization() {
        return getHeader(AUTHORIZATION_HEADER_NAME);
    }

    List<RestAttribute> getAttributes();

    default boolean containsAttributes(Class<? extends RestAttribute> type) {
        return getAttributes().stream().anyMatch(type::isInstance);
    }

    default Optional<Object> getAttribute(Class<? extends RestAttribute> type, String name) {
        return getAttributes()
            .stream()
            .filter(type::isInstance)
            .filter(attribute -> Objects.equals(attribute.getName(), name))
            .findFirst()
            .map(RestAttribute::getValue);
    }

    default List<Object> getAttributes(Class<? extends RestAttribute> type, String name) {
        return getAttributes()
            .stream()
            .filter(type::isInstance)
            .filter(attribute -> Objects.equals(attribute.getName(), name))
            .map(RestAttribute::getValue)
            .toList();
    }

    @SuppressWarnings("unchecked")
    default <T extends RestAttribute> List<T> getAttributes(Class<T> type) {
        return (List<T>) getAttributes().stream().filter(type::isInstance).toList();
    }

    RestCall body(Object body);

    RestCall contentType(MediaType contentType);

    default RestCall contentTypeJson() {
        return contentType(MediaType.APPLICATION_JSON);
    }

    default RestCall contentTypeXML() {
        return contentType(MediaType.APPLICATION_XML);
    }

    default RestCall contentTypeForm() {
        return contentType(MediaType.APPLICATION_FORM);
    }

    RestCall header(String name, String... value);

    default String getHeader(String name) {
        return (String) getAttribute(RestHeader.class, name).orElse(null);
    }

    RestCall headers(String name, Collection<String> values);

    RestCall replaceHeader(String name, String... values);

    RestCall replaceHeaders(String name, Collection<String> values);

    RestCall removeHeaders(String... names);

    default boolean containsHeaders() {
        return containsAttributes(RestHeader.class);
    }

    default List<Object> getHeaders(String name) {
        return getAttributes(RestHeader.class, name);
    }

    default List<RestHeader> getHeaders() {
        return getAttributes(RestHeader.class);
    }

    default RestCall variable(String name, Object value) {
        return variable(name, new Object[] { value });
    }

    /**
     * Add a variable.
     *
     * @param name the name
     * @param values the values (only the first will be used)
     * @return a new RestCall instance
     * @deprecated there is no use for multiple variable values with the same name. Use the
     * {@link #variable(String, Object)} method.
     */
    @Deprecated
    RestCall variable(String name, Object... values);

    default boolean containsVariables() {
        return containsAttributes(RestVariable.class);
    }

    default Object getVariable(String name) {
        return getAttribute(RestVariable.class, name).orElse(null);
    }

    default List<RestVariable> getVariables() {
        return getAttributes(RestVariable.class);
    }

    RestCall parameter(String name, Object... value);

    default Object getParameter(String name) {
        return getAttribute(RestParameter.class, name).orElse(null);
    }

    RestCall parameters(String name, Collection<?> values);

    RestCall parameters(Collection<? extends Pair<String, ?>> values);

    RestCall replaceParameter(String name, String... values);

    RestCall replaceParameters(String name, Collection<String> values);

    RestCall replaceParameters(Collection<? extends Pair<String, ?>> values);

    RestCall removeParameters(String... names);

    default boolean containsParameters() {
        return containsAttributes(RestParameter.class);
    }

    default List<Object> getParameters(String name) {
        return getAttributes(RestParameter.class, name);
    }

    default List<RestParameter> getParameters() {
        return getAttributes(RestParameter.class);
    }

    default <T> T get(Class<T> responseType) throws RestException {
        return invoke(RestMethod.GET, responseType).getBody();
    }

    @Deprecated
    default <T> T get(String path, Class<T> responseType) throws RestException {
        return invoke(RestMethod.GET, path, responseType).getBody();
    }

    default <T> T get(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.GET, responseType).getBody();
    }

    @Deprecated
    default <T> T get(String path, GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.GET, path, responseType).getBody();
    }

    default <T> T put(Class<T> responseType) throws RestException {
        return invoke(RestMethod.PUT, responseType).getBody();
    }

    @Deprecated
    default <T> T put(String path, Class<T> responseType) throws RestException {
        return invoke(RestMethod.PUT, path, responseType).getBody();
    }

    default <T> T put(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.PUT, responseType).getBody();
    }

    @Deprecated
    default <T> T put(String path, GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.PUT, path, responseType).getBody();
    }

    default <T> T post(Class<T> responseType) throws RestException {
        return invoke(RestMethod.POST, responseType).getBody();
    }

    @Deprecated
    default <T> T post(String path, Class<T> responseType) throws RestException {
        return invoke(RestMethod.POST, path, responseType).getBody();
    }

    default <T> T post(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.POST, responseType).getBody();
    }

    @Deprecated
    default <T> T post(String path, GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.POST, path, responseType).getBody();
    }

    default <T> T delete(Class<T> responseType) throws RestException {
        return invoke(RestMethod.DELETE, responseType).getBody();
    }

    @Deprecated
    default <T> T delete(String path, Class<T> responseType) throws RestException {
        return invoke(RestMethod.DELETE, path, responseType).getBody();
    }

    default <T> T delete(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.DELETE, responseType).getBody();
    }

    @Deprecated
    default <T> T delete(String path, GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.DELETE, path, responseType).getBody();
    }

    default <T> T options(Class<T> responseType) throws RestException {
        return invoke(RestMethod.OPTIONS, responseType).getBody();
    }

    @Deprecated
    default <T> T options(String path, Class<T> responseType) throws RestException {
        return invoke(RestMethod.OPTIONS, path, responseType).getBody();
    }

    default <T> T options(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.OPTIONS, responseType).getBody();
    }

    @Deprecated
    default <T> T options(String path, GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.OPTIONS, path, responseType).getBody();
    }

    default <T> T patch(Class<T> responseType) throws RestException {
        return invoke(RestMethod.PATCH, responseType).getBody();
    }

    @Deprecated
    default <T> T patch(String path, Class<T> responseType) throws RestException {
        return invoke(RestMethod.PATCH, path, responseType).getBody();
    }

    default <T> T patch(GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.PATCH, responseType).getBody();
    }

    @Deprecated
    default <T> T patch(String path, GenericType<T> responseType) throws RestException {
        return invoke(RestMethod.PATCH, path, responseType).getBody();
    }

    <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType) throws RestException;

    <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType) throws RestException;

    @Deprecated
    <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType) throws RestException;

    @Deprecated
    <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType) throws RestException;
}
