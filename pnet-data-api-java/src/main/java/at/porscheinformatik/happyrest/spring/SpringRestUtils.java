package at.porscheinformatik.happyrest.spring;

import at.porscheinformatik.happyrest.MediaType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class SpringRestUtils {

    public static org.springframework.http.MediaType convertMediaType(
        MediaType contentType,
        org.springframework.http.MediaType defaulType
    ) {
        return contentType != null
            ? org.springframework.http.MediaType.parseMediaType(contentType.toString())
            : defaulType;
    }

    public static List<org.springframework.http.MediaType> convertMediaTypes(List<MediaType> contentTypes) {
        if (contentTypes == null) {
            return null;
        }

        return contentTypes
            .stream()
            .map(contentType -> convertMediaType(contentType, null))
            .filter(Objects::nonNull)
            .toList();
    }

    public static MediaType convertMediaType(org.springframework.http.MediaType contentType, MediaType defaultType) {
        return contentType != null ? MediaType.parse(contentType.toString()) : defaultType;
    }

    /**
     * Encodes all + characters in the query string as %2B, because Spring's UriComponentsBuilder decodes them as space characters,
     * which led to a problem for example for email-addresses containing a + character.
     * There ist a Spring Issue for this: https://github.com/spring-projects/spring-framework/issues/20750 but this is a workaround.
     */
    public static URI encodePlusInQuery(URI uri) throws URISyntaxException {
        String query = uri.getRawQuery();

        if (query == null || !query.contains("+")) {
            return uri;
        }

        String raw = uri.toASCIIString();
        int queryStart = raw.indexOf('?');

        return new URI(raw.substring(0, queryStart) + "?" + query.replace("+", "%2B"));
    }

    private SpringRestUtils() {}
}

