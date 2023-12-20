package at.porscheinformatik.happyrest.apache5;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestException;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestResponseException;
import at.porscheinformatik.happyrest.RestUtils;

/**
 * Wrapper for a HttpClient response
 *
 * @param <T> the type of result object
 * @author HAM
 */
class Apache5RestResponse<T> implements RestResponse<T>
{
    private static final ZoneId GMT = ZoneId.of("GMT");

    private static final DateTimeFormatter[] DATE_PARSERS = new DateTimeFormatter[]{
        DateTimeFormatter.RFC_1123_DATE_TIME,
        DateTimeFormatter.ofPattern("EEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
        DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy", Locale.US).withZone(GMT)
    };

    @SuppressWarnings("unchecked")
    public static <T> Apache5RestResponse<T> create(RestParser parser, ClassicHttpResponse response,
        GenericType<T> type) throws RestException
    {
        int statusCode = response.getCode();
        String statusMessage = RestUtils.getHttpStatusMessage(statusCode, response.getReasonPhrase());
        Header[] headers = response.getHeaders();
        HttpEntity entity = response.getEntity();
        String entityContentType = entity.getContentType();
        ContentType contentType = entityContentType != null ? ContentType.parse(entityContentType) : null;
        long contentLength = entity.getContentLength();
        T body = null;

        if (statusCode >= 400)
        {
            try (InputStream in = entity.getContent())
            {
                throw new RestResponseException(RestUtils.toErrorResult(parser, statusCode, statusMessage, in),
                    statusCode, statusMessage, null);
            }
            catch (IOException e)
            {
                throw new RestResponseException("Failed to read response", statusCode, statusMessage, e);
            }
        }

        if (contentLength != 0)
        {
            try
            {
                try (InputStream in = entity.getContent())
                {
                    body = (T) parser.parse(Apache5RestUtils.convertMediaType(contentType).orElse(null), type, in);
                }
            }
            catch (IOException e)
            {
                throw new RestResponseException("Failed to read response", statusCode, statusMessage, e);
            }
        }

        return new Apache5RestResponse<>(statusCode, statusMessage, headers,
            contentType != null ? MediaType.parse(contentType.toString()) : null, contentLength, body);
    }

    private final int statusCode;
    private final String statusMessage;
    private final Header[] headers;
    private final MediaType contentType;
    private final long contentLength;
    private final T content;

    Apache5RestResponse(int statusCode, String statusMessage, Header[] headers, MediaType contentType,
        long contentLength, T content)
    {
        super();
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = headers;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.content = content;
    }

    @Override
    public int getStatusCode()
    {
        return statusCode;
    }

    @Override
    public String getStatusMessage()
    {
        return statusMessage;
    }

    @Override
    public boolean isInformational()
    {
        return statusCode >= 100 && statusCode < 200;
    }

    @Override
    public boolean isSuccessful()
    {
        return statusCode >= 200 && statusCode < 300;
    }

    @Override
    public boolean isRedirection()
    {
        return statusCode >= 300 && statusCode < 400;
    }

    @Override
    public boolean isError()
    {
        return statusCode >= 400;
    }

    @Override
    public T getBody()
    {
        return content;
    }

    @Override
    public List<String> getHeader(String key)
    {
        return getHeaders(headers, key);
    }

    @Override
    public String getFirstHeader(String key)
    {
        return getFirstHeader(headers, key);
    }

    @Override
    public String getCacheControl()
    {
        return getFirstHeader("Cache-Control");
    }

    @Override
    public MediaType getContentType()
    {
        return contentType;
    }

    @Override
    public Locale getContentLanguage()
    {
        String language = getFirstHeader("Content-Language");

        return language != null ? Locale.forLanguageTag(language) : null;
    }

    @Override
    public long getContentLength()
    {
        return contentLength;
    }

    @Override
    public long getCreationDate()
    {
        String date = getFirstHeader("Date");

        return date != null ? Long.parseLong(date) : -1;
    }

    @Override
    public long getExpiresDate()
    {
        return toDate(getFirstHeader("Expires"));
    }

    @Override
    public long getLastModified()
    {
        return toDate(getFirstHeader("Last-Modified"));
    }

    private static List<String> getHeaders(Header[] headers, String key)
    {
        return Arrays
            .stream(headers)
            .filter(header -> key.equalsIgnoreCase(header.getName()))
            .map(Header::getValue)
            .toList();
    }

    private static String getFirstHeader(Header[] headers, String key)
    {
        return Arrays
            .stream(headers)
            .filter(header -> key.equalsIgnoreCase(header.getName()))
            .map(Header::getValue)
            .findFirst()
            .orElse(null);
    }

    private static long toDate(String value)
    {
        if (value == null)
        {
            return -1;
        }

        ZonedDateTime zonedDateTime = null;

        if (value.length() >= 3)
        {
            int parametersIndex = value.indexOf(';');

            if (parametersIndex != -1)
            {
                value = value.substring(0, parametersIndex);
            }

            for (DateTimeFormatter dateFormatter : DATE_PARSERS)
            {
                try
                {
                    zonedDateTime = ZonedDateTime.parse(value, dateFormatter);
                    break;
                }
                catch (DateTimeParseException ex)
                {
                    // ignore
                }
            }
        }

        if (zonedDateTime == null)
        {
            return -1;
        }

        return zonedDateTime.toInstant().toEpochMilli();
    }
}
