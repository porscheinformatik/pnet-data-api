package at.porscheinformatik.happyrest.apache;

import java.net.ProxySelector;
import java.time.Duration;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestUtils;
import at.porscheinformatik.happyrest.jackson.JacksonBasedFormatter;
import at.porscheinformatik.happyrest.jackson.JacksonBasedParser;
import at.porscheinformatik.happyrest.util.ByteArrayParser;
import at.porscheinformatik.happyrest.util.CharArrayParser;
import at.porscheinformatik.happyrest.util.StringParser;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;

/**
 * A {@link RestCall} using the Apache HTTP Client
 *
 * @author HAM
 */
public class ApacheRestCallFactory implements RestCallFactory
{

    public static ApacheRestCallFactory create(RestLoggerAdapter loggerAdapter, ObjectMapper mapper)
    {
        RestFormatter formatter = RestFormatter.of(new JacksonBasedFormatter(mapper), new TextPlainFormatter());
        RestParser parser = RestParser
            .of(new JacksonBasedParser(mapper), StringParser.INSTANCE, CharArrayParser.INSTANCE,
                ByteArrayParser.INSTANCE);

        return new ApacheRestCallFactory(loggerAdapter, formatter, parser);
    }

    private final RestLoggerAdapter loggerAdapter;
    private final RestFormatter formatter;
    private final RestParser parser;
    private final ProxySelector proxySelector;
    private final Duration timeout;
    private final String userAgent;

    private CloseableHttpClient httpClient;

    public ApacheRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser)
    {
        this(loggerAdapter, formatter, parser, ProxySelector.getDefault(), null,
            RestUtils.getUserAgent("Apache's HttpClient"));
    }

    public ApacheRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser,
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

    protected ApacheRestCallFactory copy(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser,
        ProxySelector proxySelector, Duration timeout, String userAgent)
    {
        return new ApacheRestCallFactory(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public ApacheRestCallFactory withLoggerAdapter(RestLoggerAdapter loggerAdapter)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public ApacheRestCallFactory withFormatter(RestFormatter formatter)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public ApacheRestCallFactory withParser(RestParser parser)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public ApacheRestCallFactory withProxy(ProxySelector proxySelector)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public ApacheRestCallFactory withTimeout(Duration timeout)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public ApacheRestCallFactory withUserAgent(String userAgent)
    {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    @Override
    public RestCall url(String url)
    {
        if (httpClient == null)
        {
            HttpClientBuilder builder = HttpClients.custom();

            if (proxySelector != null)
            {
                builder = builder.setRoutePlanner(new SystemDefaultRoutePlanner(proxySelector));
            }

            if (timeout != null)
            {
                builder = builder
                    .setDefaultRequestConfig(RequestConfig
                        .custom()
                        .setConnectTimeout((int) timeout.toMillis())
                        .setSocketTimeout((int) timeout.toMillis())
                        .build());
            }

            if (userAgent != null)
            {
                builder.setUserAgent(userAgent);
            }

            httpClient = builder.build();
        }

        return new ApacheRestCall(httpClient, loggerAdapter, url, null, "application/json", null, formatter, parser,
            null);
    }

}
