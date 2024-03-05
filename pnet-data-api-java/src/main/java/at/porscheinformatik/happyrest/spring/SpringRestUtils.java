package at.porscheinformatik.happyrest.spring;

import java.util.List;
import java.util.Objects;

import at.porscheinformatik.happyrest.MediaType;

public class SpringRestUtils
{
    public static org.springframework.http.MediaType convertMediaType(MediaType contentType,
        org.springframework.http.MediaType defaulType)
    {
        return contentType != null ?
            org.springframework.http.MediaType.parseMediaType(contentType.toString()) :
            defaulType;
    }

    public static List<org.springframework.http.MediaType> convertMediaTypes(List<MediaType> contentTypes)
    {
        if (contentTypes == null)
        {
            return null;
        }

        return contentTypes
            .stream()
            .map(contentType -> convertMediaType(contentType, null))
            .filter(Objects::nonNull)
            .toList();
    }

    public static MediaType convertMediaType(org.springframework.http.MediaType contentType, MediaType defaultType)
    {
        return contentType != null ? MediaType.parse(contentType.toString()) : defaultType;
    }
}
