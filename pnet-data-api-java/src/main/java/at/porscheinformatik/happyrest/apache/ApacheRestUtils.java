package at.porscheinformatik.happyrest.apache;

import org.apache.http.entity.ContentType;

import at.porscheinformatik.happyrest.MediaType;

public class ApacheRestUtils
{
    public static ContentType convertMediaType(MediaType contentType, ContentType defaultType)
    {
        return contentType != null ? ContentType.parse(contentType.toString()) : defaultType;
    }

    public static MediaType convertMediaType(ContentType contentType, MediaType defaultType)
    {
        return contentType != null ? MediaType.parse(contentType.toString()) : defaultType;
    }
}
