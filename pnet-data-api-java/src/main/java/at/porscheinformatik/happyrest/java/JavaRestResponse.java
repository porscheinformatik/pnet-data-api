package at.porscheinformatik.happyrest.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalLong;

import at.porscheinformatik.happyrest.GenericType;
import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestException;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestResponse;
import at.porscheinformatik.happyrest.RestResponseException;
import at.porscheinformatik.happyrest.RestUtils;

/**
 * Wrapper for a HttpClient response
 *
 * @author HAM
 * @param <T> the type of result object
 */
class JavaRestResponse<T> implements RestResponse<T>
{

    private static final ZoneId GMT = ZoneId.of("GMT");

    private static final DateTimeFormatter[] DATE_PARSERS = new DateTimeFormatter[]{
        DateTimeFormatter.RFC_1123_DATE_TIME,
        DateTimeFormatter.ofPattern("EEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
        DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy", Locale.US).withZone(GMT)};

    @SuppressWarnings("unchecked")
    public static <T> RestResponse<T> create(RestParser parser, HttpResponse<InputStream> response, GenericType<T> type,
        RestLoggerAdapter loggerAdapter) throws RestException
    {
        int statusCode = response.statusCode();
        String statusMessage = RestUtils.getHttpStatusMessage(statusCode);
        HttpHeaders headers = response.headers();
        Optional<MediaType> optionalContentType = headers.firstValue("Content-Type").map(MediaType::parse);
        OptionalLong optionalContentLength = headers.firstValueAsLong("Content-Length");
        T body = null;

        if (statusCode >= 400)
        {
            try (InputStream stream = response.body())
            {
                try (Reader reader = new InputStreamReader(stream))
                {
                    throw new RestResponseException(RestUtils.readFully(reader), statusCode, statusMessage, null);
                }
            }
            catch (IOException e)
            {
                throw new RestResponseException("Failed to read response", statusCode, statusMessage, e);
            }
        }

        if (!type.isAssignableFrom(Void.class))
        {
            try (InputStream in = response.body())
            {
                if (in != null)
                {
                    body = (T) parser.parse(optionalContentType, type, in);
                }
            }
            catch (IOException e)
            {
                throw new RestResponseException("Failed to read response", statusCode, statusMessage, e);
            }
        }

        return new JavaRestResponse<>(statusCode, statusMessage, headers, optionalContentType.orElse(null),
            optionalContentLength.orElse(-1), body);
    }

    private final int statusCode;
    private final String statusMessage;
    private final HttpHeaders headers;
    private final MediaType contentType;
    private final long contentLength;
    private final T content;

    JavaRestResponse(int statusCode, String statusMessage, HttpHeaders headers, MediaType contentType,
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
        return this.headers.allValues(key);
    }

    @Override
    public String getFirstHeader(String key)
    {
        return this.headers.firstValue(key).orElse(null);
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
