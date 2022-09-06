package at.porscheinformatik.happyrest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaType
{
    private static final String WILDCARD = "*";

    public static MediaType ANY = parse("*");

    public static MediaType APPLICATION = parse("application/*");
    public static MediaType APPLICATION_ATOM_XML = parse("application/atom+xml");
    public static MediaType APPLICATION_FORM = parse("application/x-www-form-urlencoded");
    public static MediaType APPLICATION_JSON = parse("application/json");
    public static MediaType APPLICATION_JSON_UTF8 = parse("application/json;charset=UTF-8");
    public static MediaType APPLICATION_ANY_JSON = parse("application/*+json");
    public static MediaType APPLICATION_OCTET_STREAM = parse("application/octet-stream");
    public static MediaType APPLICATION_PDF = parse("application/pdf");
    public static MediaType APPLICATION_RSS_XML = parse("application/rss+xml");
    public static MediaType APPLICATION_XHTML_XML = parse("application/xhtml+xml");
    public static MediaType APPLICATION_ANY_XML = parse("application/*+json");
    public static MediaType APPLICATION_XML = parse("application/xml");

    public static MediaType IMAGE = parse("image/*");
    public static MediaType IMAGE_GIF = parse("image/gif");
    public static MediaType IMAGE_JPEG = parse("image/jpeg");
    public static MediaType IMAGE_PNG = parse("image/png");

    public static MediaType TEXT = parse("text/*");
    public static MediaType TEXT_HTML = parse("text/html");
    public static MediaType TEXT_MARKDOWN = parse("text/markdown");
    public static MediaType TEXT_PLAIN = parse("text/plain");
    public static MediaType TEXT_PLAIN_UTF8 = parse("text/plain;charset=UTF-8");
    public static MediaType TEXT_XML = parse("text/xml");

    public static MediaType parse(String mediaType)
    {
        if (mediaType == null)
        {
            return null;
        }

        if (mediaType.trim().isEmpty())
        {
            return ANY;
        }

        List<String> parameters = new ArrayList<>();
        int parameterIndex = mediaType.lastIndexOf(";");

        while (parameterIndex >= 0)
        {
            parameters.add(mediaType.substring(parameterIndex + 1));
            mediaType = mediaType.substring(0, parameterIndex);

            parameterIndex = mediaType.lastIndexOf(";");
        }

        String suffix;
        int suffixIndex = mediaType.lastIndexOf("+");

        if (suffixIndex >= 0)
        {
            suffix = mediaType.substring(suffixIndex + 1);
            mediaType = mediaType.substring(0, suffixIndex);
        }
        else
        {
            suffix = WILDCARD;
        }

        String subtype;
        int subtypeIndex = mediaType.indexOf("/");

        if (subtypeIndex >= 0)
        {
            subtype = mediaType.substring(subtypeIndex + 1);
            mediaType = mediaType.substring(0, subtypeIndex);
        }
        else
        {
            subtype = WILDCARD;
        }

        return new MediaType(mediaType, subtype, suffix, parameters.toArray(new String[parameters.size()]));
    }

    public static String format(Iterable<MediaType> mediaTypes)
    {
        StringBuilder builder = new StringBuilder();
        Iterator<MediaType> iterator = mediaTypes.iterator();

        while (iterator.hasNext())
        {
            builder.append(iterator.next().toString());

            if (iterator.hasNext())
            {
                builder.append(", ");
            }
        }

        return builder.toString();
    }

    private final String type;
    private final String subtype;
    private final String suffix;
    private final String[] parameters;

    public MediaType(String type, String subtype, String suffix, String... parameters)
    {
        super();
        this.type = type;
        this.subtype = subtype;
        this.suffix = suffix;
        this.parameters = parameters;
    }

    public String getType()
    {
        return type;
    }

    public String getSubtype()
    {
        return subtype;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public String[] getParameters()
    {
        return parameters;
    }

    public String getParameter(String name)
    {
        for (String parameter : parameters)
        {
            String key = getKey(parameter);

            if (name.equalsIgnoreCase(key))
            {
                return getValue(parameter);
            }
        }

        return null;
    }

    public Charset getCharset(Charset defaultCharset)
    {
        String charset = getParameter("charset");

        if (charset == null || charset.trim().isEmpty())
        {
            return defaultCharset;
        }

        try
        {
            return Charset.forName(charset);
        }
        catch (IllegalArgumentException e)
        {
            return defaultCharset;
        }
    }

    public boolean isCompatible(MediaType other)
    {
        if ((other == null) || (!type.equals(WILDCARD) && !type.equalsIgnoreCase(other.type)))
        {
            return false;
        }

        if (!subtype.equals(WILDCARD) && !subtype.equalsIgnoreCase(other.subtype))
        {
            return false;
        }

        if (!suffix.equals(WILDCARD) && !suffix.equalsIgnoreCase(other.suffix))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder(type).append("/").append(subtype);

        if (!suffix.equals(WILDCARD))
        {
            builder.append("+").append(suffix);
        }

        for (String parameter : parameters)
        {
            builder.append(";").append(parameter);
        }

        return builder.toString();
    }

    private static String getKey(String s)
    {
        if (s == null)
        {
            return null;
        }

        int index = s.indexOf("=");

        if (index < 0)
        {
            return s;
        }

        return s.substring(0, index).trim();
    }

    private static String getValue(String s)
    {
        if (s == null)
        {
            return null;
        }

        int index = s.indexOf("=");

        if (index < 0)
        {
            return null;
        }

        return s.substring(index + 1).trim();
    }

}
