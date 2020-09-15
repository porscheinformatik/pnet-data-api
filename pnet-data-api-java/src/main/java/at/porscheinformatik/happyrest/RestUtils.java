package at.porscheinformatik.happyrest;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.StreamUtils;

/**
 * Utilities for the framework.
 *
 * @author ham
 */
public final class RestUtils
{

    private static final Map<Integer, String> HTTP_STATUS_MESSAGES;

    private static String version;

    static
    {
        Map<Integer, String> httpStatusMessages = new HashMap<>();

        httpStatusMessages.put(100, "Continue");
        httpStatusMessages.put(101, "Switching Protocols");
        httpStatusMessages.put(102, "Processing");
        httpStatusMessages.put(200, "OK");
        httpStatusMessages.put(201, "Created");
        httpStatusMessages.put(202, "Accepted");
        httpStatusMessages.put(203, "Non-Authoritative Information");
        httpStatusMessages.put(204, "No Content");
        httpStatusMessages.put(205, "Reset Content");
        httpStatusMessages.put(206, "Partial Content");
        httpStatusMessages.put(207, "Multi-Status");
        httpStatusMessages.put(208, "Already Reported");
        httpStatusMessages.put(226, "IM Used");
        httpStatusMessages.put(300, "Multiple Choices");
        httpStatusMessages.put(301, "Moved Permanently");
        httpStatusMessages.put(302, "Found (Moved Temporarily)");
        httpStatusMessages.put(303, "See Other");
        httpStatusMessages.put(304, "Not Modified");
        httpStatusMessages.put(305, "Use Proxy");
        httpStatusMessages.put(307, "Temporary Redirect");
        httpStatusMessages.put(308, "Permanent Redirect");
        httpStatusMessages.put(400, "Bad Request");
        httpStatusMessages.put(401, "Unauthorized");
        httpStatusMessages.put(402, "Payment Required");
        httpStatusMessages.put(403, "Forbidden");
        httpStatusMessages.put(404, "Not Found");
        httpStatusMessages.put(405, "Method Not Allowed");
        httpStatusMessages.put(406, "Not Acceptable");
        httpStatusMessages.put(407, "Proxy Authentication Required");
        httpStatusMessages.put(408, "Request Timeout");
        httpStatusMessages.put(409, "Conflict");
        httpStatusMessages.put(410, "Gone");
        httpStatusMessages.put(411, "Length Required");
        httpStatusMessages.put(412, "Precondition Failed");
        httpStatusMessages.put(413, "Request Entity Too Large");
        httpStatusMessages.put(414, "URI Too Long");
        httpStatusMessages.put(415, "Unsupported Media Type");
        httpStatusMessages.put(416, "Requested range not satisfiable");
        httpStatusMessages.put(417, "Expectation Failed");
        httpStatusMessages.put(420, "Policy Not Fulfilled");
        httpStatusMessages.put(421, "Misdirected Request");
        httpStatusMessages.put(422, "Unprocessable Entity");
        httpStatusMessages.put(423, "Locked");
        httpStatusMessages.put(424, "Failed Dependency");
        httpStatusMessages.put(426, "Upgrade Required");
        httpStatusMessages.put(428, "Precondition Required");
        httpStatusMessages.put(429, "Too Many Requests");
        httpStatusMessages.put(431, "Request Header Fields Too Large");
        httpStatusMessages.put(451, "Unavailable For Legal Reasons");
        httpStatusMessages.put(418, "I’m a teapot");
        httpStatusMessages.put(425, "Unordered Collection");
        httpStatusMessages.put(444, "No Response");
        httpStatusMessages.put(449, "The request should be retried after doing the appropriate action");
        httpStatusMessages.put(499, "Client Closed Request");
        httpStatusMessages.put(500, "Internal Server Error");
        httpStatusMessages.put(501, "Not Implemented");
        httpStatusMessages.put(502, "Bad Gateway");
        httpStatusMessages.put(503, "Service Unavailable");
        httpStatusMessages.put(504, "Gateway Timeout");
        httpStatusMessages.put(505, "HTTP Version not supported");
        httpStatusMessages.put(506, "Variant Also Negotiates");
        httpStatusMessages.put(507, "Insufficient Storage");
        httpStatusMessages.put(508, "Loop Detected");
        httpStatusMessages.put(509, "Bandwidth Limit Exceeded");
        httpStatusMessages.put(510, "Not Extended");
        httpStatusMessages.put(511, "Network Authentication Required");

        HTTP_STATUS_MESSAGES = Collections.unmodifiableMap(httpStatusMessages);
    }

    public static String getVersion()
    {
        String version = RestUtils.version;

        if (version == null)
        {
            version = "UNDEFINED";

            try (InputStream stream = RestUtils.class
                .getClassLoader()
                .getResourceAsStream("META-INF/maven/at.porscheinformatik.pnet/pnet-data-api-java/pom.properties"))
            {
                if (stream != null)
                {
                    Properties properties = new Properties();

                    properties.load(stream);

                    version = properties.getProperty("version");
                }
                else
                {
                    System.err.println("Failed to determine version of HappyRest. Using \"UNDEFINED\" as version.");
                }
            }
            catch (IOException e)
            {
                System.err.println("Failed to determine version of HappyRest (using \"UNDEFINED\" as version): " + e);
            }

            RestUtils.version = version;
        }

        return version;
    }

    public static String getUserAgent(String technology)
    {
        return String
            .format("HappyRest %s using %s (%s; %s) %s %s", getVersion(), technology, System.getProperty("os.name"),
                System.getProperty("os.arch"), System.getProperty("java.runtime.name"),
                System.getProperty("java.runtime.version"));
    }

    private RestUtils()
    {
        super();
    }

    // CHECKSTYLE:OFF
    private static boolean isAllowedInPathSegment(byte c)
    {
        return (c >= 'a' && c <= 'z')
            || (c >= 'A' && c <= 'Z')
            || (c >= '0' && c <= '9')
            || '-' == c
            || '.' == c
            || '_' == c
            || '~' == c
            || '!' == c
            || '$' == c
            || '&' == c
            || '\'' == c
            || '(' == c
            || ')' == c
            || '*' == c
            || '+' == c
            || ',' == c
            || ';' == c
            || '=' == c
            || ':' == c
            || '@' == c;
    }
    // CHECKSTYLE:ON

    public static String encodePathSegment(String pathSegment, Charset charset, boolean ignorePlaceholders)
    {
        byte[] bytes = pathSegment.getBytes(charset);
        boolean original = true;

        for (byte b : bytes)
        {
            if (!isAllowedInPathSegment(b))
            {
                original = false;
                break;
            }
        }

        if (original)
        {
            return pathSegment;
        }

        boolean inPlaceholder = false;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);

        for (byte b : bytes)
        {
            if (isAllowedInPathSegment(b))
            {
                baos.write(b);
            }
            else if (ignorePlaceholders && '{' == b)
            {
                inPlaceholder = true;
                baos.write(b);
            }
            else if (ignorePlaceholders && inPlaceholder && '}' == b)
            {
                inPlaceholder = false;
                baos.write(b);
            }
            else if (ignorePlaceholders && inPlaceholder)
            {
                baos.write(b);
            }
            else
            {
                baos.write('%');
                char hex1 = Character.toUpperCase(Character.forDigit((b >> 4) & 0xF, 16));
                char hex2 = Character.toUpperCase(Character.forDigit(b & 0xF, 16));
                baos.write(hex1);
                baos.write(hex2);
            }
        }

        return StreamUtils.copyToString(baos, charset);
    }

    public static String appendPathWithPlaceholders(String url, String path)
    {
        return appendPath(url, true, true, path);
    }

    public static String appendPath(String url, String path)
    {
        return appendPath(url, true, false, path);
    }

    private static String appendPath(String url, boolean encode, boolean ignorePlaceholders, String path)
    {
        if (path == null || path.length() == 0)
        {
            return url;
        }

        return appendPathSegments(url, encode, ignorePlaceholders, path.split("/"));
    }

    public static String appendPathSegmentsWithPlaceholders(String url, String... pathSegments)
    {
        return appendPathSegments(url, true, true, pathSegments);
    }

    public static String appendPathSegments(String url, String... pathSegments)
    {
        return appendPathSegments(url, true, false, pathSegments);
    }

    public static String appendEncodedPathSegments(String url, String... pathSegments)
    {
        return appendPathSegments(url, false, true, pathSegments);
    }

    private static String appendPathSegments(String url, boolean encode, boolean ignorePlaceholders,
        String... pathSegments)
    {
        if (pathSegments == null || pathSegments.length == 0)
        {
            return url;
        }

        for (String pathSegment : pathSegments)
        {
            if (pathSegment.length() == 0)
            {
                continue;
            }

            if (!url.endsWith("/"))
            {
                url += "/";
            }

            if (pathSegment.startsWith("/"))
            {
                pathSegment = pathSegment.substring(1);
            }

            if (encode)
            {
                pathSegment = RestUtils.encodePathSegment(pathSegment, StandardCharsets.UTF_8, ignorePlaceholders);
            }

            url += pathSegment;
        }

        return url;
    }

    public static String getHttpStatusMessage(int code)
    {
        String result = HTTP_STATUS_MESSAGES.get(code);

        return result != null ? result : "undefined";
    }

    public static String getHttpStatusMessage(int statusCode, String statusMessage)
    {
        if (statusMessage != null && statusMessage.length() > 0)
        {
            String statusCodeAsString = String.valueOf(statusCode);

            if (statusMessage.startsWith(statusCodeAsString))
            {
                statusMessage = statusMessage.substring(statusCodeAsString.length(), statusMessage.length()).trim();
            }
        }

        if (statusMessage == null || statusMessage.length() == 0)
        {
            statusMessage = RestUtils.getHttpStatusMessage(statusCode);
        }

        return statusMessage;
    }

    public static String getHttpStatus(int statusCode, String statusMessage)
    {
        return statusCode + " " + getHttpStatusMessage(statusCode, statusMessage);
    }

    public static String extractContentType(String contentType)
    {
        if (contentType == null)
        {
            return null;
        }

        int delimiter = contentType.indexOf(";");

        return delimiter >= 0 ? contentType.substring(0, delimiter) : contentType;
    }

    public static String extractContentTypeParameter(String contentType)
    {
        if (contentType == null)
        {
            return null;
        }

        int delimiter = contentType.indexOf(";");

        return delimiter >= 0 ? contentType.substring(delimiter + 1) : null;
    }

    public static Charset extractContentTypeCharset(String contentType)
    {
        if (contentType == null)
        {
            return null;
        }

        String[] parts = contentType.split(";");

        for (String part : parts)
        {
            if ("charset".equals(getKey(part)))
            {
                return Charset.forName(getValue(part));
            }
        }

        return StandardCharsets.UTF_8;
    }

    public static String getKey(String s)
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

    public static String getValue(String s)
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

    public static String abbreviate(String s, int length)
    {
        if (s == null)
        {
            return null;
        }

        if (s.length() < length)
        {
            return s;
        }

        return s.substring(0, Math.max(length - 3, 0)) + "...";
    }

    public static String readFully(Reader reader) throws IOException
    {
        return String.valueOf(readAllChars(reader));
    }

    public static byte[] readAllBytes(InputStream in) throws IOException
    {
        byte[] buffer = new byte[8192];
        int length;

        try (ByteArrayOutputStream result = new ByteArrayOutputStream())
        {
            while ((length = in.read(buffer)) >= 0)
            {
                result.write(buffer, 0, length);
            }

            return result.toByteArray();
        }

        // seems to be buggy, it causes the pnet.data.webapp.person.PersonDataImageIT.getPortrait(ClientTechnology) to fail
        // return in.readAllBytes();
    }

    public static char[] readAllChars(Reader reader) throws IOException
    {
        char[] buffer = new char[8192];
        int length;

        try (CharArrayWriter result = new CharArrayWriter())
        {
            while ((length = reader.read(buffer)) >= 0)
            {
                result.write(buffer, 0, length);
            }

            return result.toCharArray();
        }
    }

}
