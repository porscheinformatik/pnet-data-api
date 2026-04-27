package at.porscheinformatik.happyrest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaType {

    private static final String WILDCARD = "*";

    public static final MediaType ANY = MediaType.parse("*");

    public static final MediaType APPLICATION = MediaType.parse("application/*");
    public static final MediaType APPLICATION_ATOM_XML = MediaType.parse("application/atom+xml");
    public static final MediaType APPLICATION_FORM = MediaType.parse("application/x-www-form-urlencoded");
    public static final MediaType APPLICATION_JSON = MediaType.parse("application/json");
    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.parse("application/json;charset=UTF-8");
    public static final MediaType APPLICATION_ANY_JSON = MediaType.parse("application/*+json");
    public static final MediaType APPLICATION_OCTET_STREAM = MediaType.parse("application/octet-stream");
    public static final MediaType APPLICATION_PDF = MediaType.parse("application/pdf");
    public static final MediaType APPLICATION_RSS_XML = MediaType.parse("application/rss+xml");
    public static final MediaType APPLICATION_XHTML_XML = MediaType.parse("application/xhtml+xml");
    public static final MediaType APPLICATION_ANY_XML = MediaType.parse("application/*+xml");
    public static final MediaType APPLICATION_XML = MediaType.parse("application/xml");

    public static final MediaType IMAGE = MediaType.parse("image/*");
    public static final MediaType IMAGE_GIF = MediaType.parse("image/gif");
    public static final MediaType IMAGE_JPEG = MediaType.parse("image/jpeg");
    public static final MediaType IMAGE_PNG = MediaType.parse("image/png");

    public static final MediaType TEXT = MediaType.parse("text/*");
    public static final MediaType TEXT_HTML = MediaType.parse("text/html");
    public static final MediaType TEXT_MARKDOWN = MediaType.parse("text/markdown");
    public static final MediaType TEXT_PLAIN = MediaType.parse("text/plain");
    public static final MediaType TEXT_PLAIN_UTF8 = MediaType.parse("text/plain;charset=UTF-8");
    public static final MediaType TEXT_XML = MediaType.parse("text/xml");

    public static MediaType parse(String mediaType) {
        if (mediaType == null) {
            return null;
        }

        if (mediaType.trim().isEmpty()) {
            return MediaType.ANY;
        }

        List<String> parameters = new ArrayList<>();
        int parameterIndex = mediaType.lastIndexOf(";");

        while (parameterIndex >= 0) {
            parameters.add(mediaType.substring(parameterIndex + 1));
            mediaType = mediaType.substring(0, parameterIndex);

            parameterIndex = mediaType.lastIndexOf(";");
        }

        String suffix;
        int suffixIndex = mediaType.lastIndexOf("+");

        if (suffixIndex >= 0) {
            suffix = mediaType.substring(suffixIndex + 1);
            mediaType = mediaType.substring(0, suffixIndex);
        } else {
            suffix = MediaType.WILDCARD;
        }

        String subtype;
        int subtypeIndex = mediaType.indexOf("/");

        if (subtypeIndex >= 0) {
            subtype = mediaType.substring(subtypeIndex + 1);
            mediaType = mediaType.substring(0, subtypeIndex);
        } else {
            subtype = MediaType.WILDCARD;
        }

        return new MediaType(mediaType, subtype, suffix, parameters.toArray(new String[parameters.size()]));
    }

    public static String format(Iterable<MediaType> mediaTypes) {
        StringBuilder builder = new StringBuilder();
        Iterator<MediaType> iterator = mediaTypes.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next().toString());

            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }

        return builder.toString();
    }

    private final String type;
    private final String subtype;
    private final String suffix;
    private final String[] parameters;

    public MediaType(String type, String subtype, String suffix, String... parameters) {
        this.type = type;
        this.subtype = subtype;
        this.suffix = suffix;
        this.parameters = parameters;
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getSuffix() {
        return suffix;
    }

    public String[] getParameters() {
        return parameters;
    }

    public String getParameter(String name) {
        for (String parameter : parameters) {
            String key = MediaType.getKey(parameter);

            if (name.equalsIgnoreCase(key)) {
                return MediaType.getValue(parameter);
            }
        }

        return null;
    }

    public Charset getCharset(Charset defaultCharset) {
        String charset = getParameter("charset");

        if (charset == null || charset.trim().isEmpty()) {
            return defaultCharset;
        }

        try {
            return Charset.forName(charset);
        } catch (IllegalArgumentException e) {
            return defaultCharset;
        }
    }

    public boolean isCompatible(MediaType other) {
        if ((other == null) || (!MediaType.WILDCARD.equals(type) && !type.equalsIgnoreCase(other.type))) {
            return false;
        }

        if (!MediaType.WILDCARD.equals(subtype) && !subtype.equalsIgnoreCase(other.subtype)) {
            return false;
        }

        if (!MediaType.WILDCARD.equals(suffix) && !suffix.equalsIgnoreCase(other.suffix)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(type).append("/").append(subtype);

        if (!MediaType.WILDCARD.equals(suffix)) {
            builder.append("+").append(suffix);
        }

        for (String parameter : parameters) {
            builder.append(";").append(parameter);
        }

        return builder.toString();
    }

    private static String getKey(String s) {
        if (s == null) {
            return null;
        }

        int index = s.indexOf("=");

        if (index < 0) {
            return s;
        }

        return s.substring(0, index).trim();
    }

    private static String getValue(String s) {
        if (s == null) {
            return null;
        }

        int index = s.indexOf("=");

        if (index < 0) {
            return null;
        }

        return s.substring(index + 1).trim();
    }
}
