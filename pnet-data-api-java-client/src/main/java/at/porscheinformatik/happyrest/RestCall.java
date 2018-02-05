package at.porscheinformatik.happyrest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A REST call. Objects of this type must be final and thread-safe!
 *
 * @author ham
 */
public interface RestCall
{

    String MEIDA_TYPE_APPLICATION_ATOM_XML = "application/atom+xml";
    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String MEDIA_TYPE_APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";
    String MEDIA_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    String MEDIA_TYPE_APPLICATION_PDF = "application/pdf";
    String MEDIA_TYPE_APPLICATION_RSS_XML = "application/rss+xml";
    String MEDIA_TYPE_APPLICATION_XHTML_XML = "application/xhtml+xml";
    String MEDIA_TYPE_APPLICATION_XML = "application/xml";
    String MEDIA_TYPE_IMAGE_GIF = "image/gif";
    String MEDIA_TYPE_IMAGE_JPEG = "image/jpeg";
    String MEDIA_TYPE_IMAGE_PNG = "image/png";
    String MEDIA_TYPE_TEXT_HTML = "text/html";
    String MEDIA_TYPE_TEXT_MARKDOWN = "text/markdown";
    String MEDIA_TYPE_TEXT_PLAIN = "text/plain";
    String MEDIA_TYPE_TEXT_XML = "text/xml";

    String getUrl();

    RestCall url(String url);

    List<String> getAcceptableMediaTypes();

    RestCall accept(String... mediaTypes);

    default RestCall acceptJson()
    {
        return accept(MEDIA_TYPE_APPLICATION_JSON_UTF8);
    }

    default RestCall acceptXML()
    {
        return accept(MEDIA_TYPE_APPLICATION_XML);
    }

    //    RestCall basicAuthorization(UsernamePasswordCredentials credentials);

    List<RestAttribute> getAttributes();

    RestCall body(Object body);

    RestCall header(String name, String value);

    default RestCall headers(String name, String... values)
    {
        Arrays.stream(values).forEach($ -> header(name, $));

        return this;
    }

    default RestCall headers(String name, Collection<String> values)
    {
        values.forEach($ -> header(name, $));

        return this;
    }

    RestCall variable(String name, Object value);

    default RestCall variables(String name, Object... values)
    {
        Arrays.stream(values).forEach($ -> variable(name, $));

        return this;
    }

    default RestCall variables(String name, Collection<Object> values)
    {
        values.forEach($ -> variable(name, $));

        return this;
    }

    RestCall parameter(String name, Object value);

    default RestCall parameters(String name, Object... values)
    {
        Arrays.stream(values).forEach($ -> parameter(name, $));

        return this;
    }

    default RestCall parameters(String name, Collection<Object> values)
    {
        values.forEach($ -> parameter(name, $));

        return this;
    }

    <T> T get(Class<T> responseType);

    <T> T get(String path, Class<T> responseType);

    <T> T get(GenericType<T> responseType);

    <T> T get(String path, GenericType<T> responseType);

    <T> T put(Class<T> responseType);

    <T> T put(String path, Class<T> responseType);

    <T> T put(GenericType<T> responseType);

    <T> T put(String path, GenericType<T> responseType);

    <T> T post(Class<T> responseType);

    <T> T post(String path, Class<T> responseType);

    <T> T post(GenericType<T> responseType);

    <T> T post(String path, GenericType<T> responseType);

    <T> T delete(Class<T> responseType);

    <T> T delete(String path, Class<T> responseType);

    <T> T delete(GenericType<T> responseType);

    <T> T delete(String path, GenericType<T> responseType);

    <T> T options(Class<T> responseType);

    <T> T options(String path, Class<T> responseType);

    <T> T options(GenericType<T> responseType);

    <T> T options(String path, GenericType<T> responseType);

    <T> T patch(Class<T> responseType);

    <T> T patch(String path, Class<T> responseType);

    <T> T patch(GenericType<T> responseType);

    <T> T patch(String path, GenericType<T> responseType);

    <T> RestResponse<T> invoke(RestMethod method, Class<T> responseType);

    <T> RestResponse<T> invoke(RestMethod method, GenericType<T> responseType);

    <T> RestResponse<T> invoke(RestMethod method, String path, Class<T> responseType);

    <T> RestResponse<T> invoke(RestMethod method, String path, GenericType<T> responseType);

}