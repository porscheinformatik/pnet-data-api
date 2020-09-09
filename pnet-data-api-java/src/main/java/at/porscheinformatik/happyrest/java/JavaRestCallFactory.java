package at.porscheinformatik.happyrest.java;

import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Builder;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestUtils;
import at.porscheinformatik.happyrest.jackson.JacksonBasedFormatter;
import at.porscheinformatik.happyrest.jackson.JacksonBasedParser;
import at.porscheinformatik.happyrest.util.BinaryParser;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;

/**
 * A {@link RestCall} using the Java Client
 *
 * @author HAM
 */
public class JavaRestCallFactory implements RestCallFactory
{

    public static JavaRestCallFactory create(RestLoggerAdapter loggerAdapter, ObjectMapper mapper)
    {
        RestFormatter formatter = RestFormatter.of(new JacksonBasedFormatter(mapper), new TextPlainFormatter());
        RestParser parser = RestParser.of(new JacksonBasedParser(mapper), BinaryParser.INSTANCE);

        return new JavaRestCallFactory(loggerAdapter, formatter, parser);
    }

    private final RestLoggerAdapter loggerAdapter;
    private final RestFormatter formatter;
    private final RestParser parser;
    private final ProxySelector proxySelector;
    private final Duration timeout;
    private final String userAgent;

    private HttpClient httpClient;

    public JavaRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser)
    {
        this(loggerAdapter, formatter, parser, ProxySelector.getDefault(), null, RestUtils.getUserAgent("Java's HttpClient"));
    }

    public JavaRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser,
        ProxySelector proxySelector, Duration timeout, String userAgent)
    {
        super();

        this.loggerAdapter = loggerAdapter;
        this.formatter = formatter;
        this.parser = parser;
        this.proxySelector = proxySelector;
        this.timeout = timeout;
        this.userAgent = userAgent;
    }

    protected JavaRestCallFactory copy(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser,
        ProxySelector proxySelector, Duration timeout, String userAgent)
    {
        return new JavaRestCallFactory(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public JavaRestCallFactory withLoggerAdapter(RestLoggerAdapter loggerAdapter)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public JavaRestCallFactory withFormatter(RestFormatter formatter)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public JavaRestCallFactory withParser(RestParser parser)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public JavaRestCallFactory withProxy(ProxySelector proxySelector)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public JavaRestCallFactory withTimeout(Duration timeout)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public JavaRestCallFactory withUserAgent(String userAgent)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    @Override
    public RestCall url(String url)
    {
        if (httpClient == null)
        {
            Builder builder = HttpClient.newBuilder();

            if (proxySelector != null)
            {
                builder = builder.proxy(proxySelector);
            }

            if (timeout != null)
            {
                builder = builder.connectTimeout(timeout);
            }

            httpClient = builder.build();
        }

        return new JavaRestCall(httpClient, userAgent, loggerAdapter, url, null, "application/json", null, formatter,
            parser, null);
    }

}
