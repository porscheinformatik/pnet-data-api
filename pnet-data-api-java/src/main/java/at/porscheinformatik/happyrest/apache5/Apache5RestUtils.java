package at.porscheinformatik.happyrest.apache5;

import at.porscheinformatik.happyrest.MediaType;
import java.util.Optional;
import org.apache.hc.core5.http.ContentType;

public class Apache5RestUtils {

    public static ContentType convertMediaType(MediaType contentType, ContentType defaultType) {
        return contentType != null ? ContentType.parse(contentType.toString()) : defaultType;
    }

    public static Optional<MediaType> convertMediaType(ContentType contentType) {
        return contentType == null ? Optional.empty() : Optional.of(MediaType.parse(contentType.toString()));
    }
}
