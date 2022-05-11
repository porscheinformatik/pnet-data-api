package at.porscheinformatik.happyrest.apache;

import java.util.Optional;

import org.apache.http.entity.ContentType;

import at.porscheinformatik.happyrest.MediaType;

public class ApacheRestUtils
{
    public static ContentType convertMediaType(MediaType contentType, ContentType defaultType)
    {
        return contentType != null ? ContentType.parse(contentType.toString()) : defaultType;
    }

    public static Optional<MediaType> convertMediaType(ContentType contentType)
    {
        return contentType == null ? Optional.empty() : Optional.of(MediaType.parse(contentType.toString()));
    }
}
