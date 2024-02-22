package at.porscheinformatik.happyrest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pnet.data.api.util.Pair;

public abstract class MockedRestResponse<T> implements RestResponse<T>
{
    private int statusCode;
    private String statusMessage;
    private boolean informational;
    private boolean successful;
    private boolean redirection;
    private boolean error;
    private T body;
    private final List<Pair<String, String>> headers = new ArrayList<>();
    private String cacheControl;
    private MediaType contentType;
    private Locale contentLanguage;
    private long contentLength;
    private long creationDate;
    private long expiresDate;
    private long lastModified;

    public abstract String getRequestMethod();

    public abstract String getRequestUrl();

    public abstract String getRequestBody();

    public abstract List<String> getRequestHeader(String key);

    public String getFirstRequestHeader(String key)
    {
        List<String> values = getRequestHeader(key);

        return values == null || values.isEmpty() ? null : values.get(0);
    }

    @Override
    public int getStatusCode()
    {
        return statusCode;
    }

    public MockedRestResponse<T> statusCode(int statusCode)
    {
        this.statusCode = statusCode;

        return this;
    }

    @Override
    public String getStatusMessage()
    {
        return statusMessage;
    }

    public MockedRestResponse<T> statusMessage(String statusMessage)
    {
        this.statusMessage = statusMessage;

        return this;
    }

    @Override
    public boolean isInformational()
    {
        return informational;
    }

    public MockedRestResponse<T> informational(boolean informational)
    {
        this.informational = informational;

        return this;
    }

    @Override
    public boolean isSuccessful()
    {
        return successful;
    }

    public MockedRestResponse<T> successful(boolean successful)
    {
        this.successful = successful;

        return this;
    }

    @Override
    public boolean isRedirection()
    {
        return redirection;
    }

    public MockedRestResponse<T> redirection(boolean redirection)
    {
        this.redirection = redirection;

        return this;
    }

    @Override
    public boolean isError()
    {
        return error;
    }

    public MockedRestResponse<T> error(boolean error)
    {
        this.error = error;

        return this;
    }

    @Override
    public T getBody()
    {
        return body;
    }

    public MockedRestResponse<T> body(T body)
    {
        this.body = body;

        return this;
    }

    public MockedRestResponse<T> addHeader(String key, String value)
    {
        headers.add(Pair.of(key, value));

        return this;
    }

    @Override
    public List<String> getHeader(String key)
    {
        return headers
            .stream()
            .filter(header -> key.equalsIgnoreCase(header.getLeft()))
            .map(Pair::getRight)
            .toList();
    }

    @Override
    public String getFirstHeader(String key)
    {
        return headers
            .stream()
            .filter(header -> key.equalsIgnoreCase(header.getLeft()))
            .map(Pair::getRight)
            .findFirst()
            .orElse(null);
    }

    @Override
    public String getCacheControl()
    {
        return cacheControl;
    }

    public MockedRestResponse<T> cacheControl(String cacheControl)
    {
        this.cacheControl = cacheControl;

        return this;
    }

    @Override
    public MediaType getContentType()
    {
        return contentType;
    }

    public MockedRestResponse<T> contentType(MediaType contentType)
    {
        this.contentType = contentType;

        return this;
    }

    @Override
    public Locale getContentLanguage()
    {
        return contentLanguage;
    }

    public MockedRestResponse<T> contentLanguage(Locale contentLanguage)
    {
        this.contentLanguage = contentLanguage;

        return this;
    }

    @Override
    public long getContentLength()
    {
        return contentLength;
    }

    public MockedRestResponse<T> contentLength(long contentLength)
    {
        this.contentLength = contentLength;

        return this;
    }

    @Override
    public long getCreationDate()
    {
        return creationDate;
    }

    public MockedRestResponse<T> creationDate(long creationDate)
    {
        this.creationDate = creationDate;

        return this;
    }

    @Override
    public long getExpiresDate()
    {
        return expiresDate;
    }

    public MockedRestResponse<T> expiresDate(long expiresDate)
    {
        this.expiresDate = expiresDate;

        return this;
    }

    @Override
    public long getLastModified()
    {
        return lastModified;
    }

    public MockedRestResponse<T> lastModified(long lastModified)
    {
        this.lastModified = lastModified;

        return this;
    }
}
