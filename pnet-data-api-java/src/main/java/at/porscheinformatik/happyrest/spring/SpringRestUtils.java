package at.porscheinformatik.happyrest.spring;

import java.util.List;
import java.util.stream.Collectors;

import at.porscheinformatik.happyrest.MediaType;

public class SpringRestUtils
{
    public static org.springframework.http.MediaType convert(MediaType contentType)
    {
        return contentType != null ? org.springframework.http.MediaType.parseMediaType(contentType.toString()) : null;
    }

    public static List<org.springframework.http.MediaType> convert(List<MediaType> contentTypes)
    {
        if (contentTypes == null)
        {
            return null;
        }

        return contentTypes.stream().map(SpringRestUtils::convert).collect(Collectors.toList());
    }

    public static MediaType convert(org.springframework.http.MediaType contentType)
    {
        return contentType != null ? MediaType.parse(contentType.toString()) : null;
    }
}
