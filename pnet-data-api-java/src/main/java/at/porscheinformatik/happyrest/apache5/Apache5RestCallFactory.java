package at.porscheinformatik.happyrest.apache5;

import at.porscheinformatik.happyrest.MediaType;
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
import at.porscheinformatik.happyrest.util.NumberParser;
import at.porscheinformatik.happyrest.util.StringParser;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;
import java.net.ProxySelector;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.routing.SystemDefaultRoutePlanner;
import tools.jackson.databind.json.JsonMapper;

/**
 * A {@link RestCall} using the Apache HTTP Client
 *
 * @author HAM
 */
public class Apache5RestCallFactory implements RestCallFactory {

    public static Apache5RestCallFactory create(RestLoggerAdapter loggerAdapter, JsonMapper mapper) {
        RestFormatter formatter = RestFormatter.of(new JacksonBasedFormatter(mapper), new TextPlainFormatter());
        RestParser parser = RestParser.of(
            StringParser.INSTANCE,
            NumberParser.INSTANCE,
            CharArrayParser.INSTANCE,
            ByteArrayParser.INSTANCE,
            new JacksonBasedParser(mapper)
        );

        return new Apache5RestCallFactory(loggerAdapter, formatter, parser);
    }

    protected final RestLoggerAdapter loggerAdapter;
    protected final RestFormatter formatter;
    protected final RestParser parser;
    protected final ProxySelector proxySelector;
    protected final Duration timeout;
    protected final String userAgent;

    protected CloseableHttpClient httpClient;

    public Apache5RestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser) {
        this(
            loggerAdapter,
            formatter,
            parser,
            ProxySelector.getDefault(),
            null,
            RestUtils.getUserAgent("Apache's HttpClient")
        );
    }

    public Apache5RestCallFactory(
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter,
        RestParser parser,
        ProxySelector proxySelector,
        Duration timeout,
        String userAgent
    ) {
        super();
        this.loggerAdapter = loggerAdapter;
        this.formatter = formatter;
        this.parser = parser;
        this.proxySelector = proxySelector;
        this.timeout = timeout;
        this.userAgent = userAgent;
    }

    protected Apache5RestCallFactory copy(
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter,
        RestParser parser,
        ProxySelector proxySelector,
        Duration timeout,
        String userAgent
    ) {
        return new Apache5RestCallFactory(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public Apache5RestCallFactory withLoggerAdapter(RestLoggerAdapter loggerAdapter) {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public Apache5RestCallFactory withFormatter(RestFormatter formatter) {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public Apache5RestCallFactory withParser(RestParser parser) {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public Apache5RestCallFactory withProxy(ProxySelector proxySelector) {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public Apache5RestCallFactory withTimeout(Duration timeout) {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public Apache5RestCallFactory withUserAgent(String userAgent) {
        return copy(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    @Override
    public RestCall url(String url) {
        if (httpClient == null) {
            HttpClientBuilder builder = HttpClients.custom();

            if (proxySelector != null) {
                builder.setRoutePlanner(new SystemDefaultRoutePlanner(proxySelector));
            }

            if (timeout != null) {
                builder.setDefaultRequestConfig(
                    RequestConfig.custom()
                        .setConnectionRequestTimeout((int) timeout.toMillis(), TimeUnit.MILLISECONDS)
                        .build()
                );
            }

            if (userAgent != null) {
                builder.setUserAgent(userAgent);
            }

            httpClient = builder.build();
        }

        return new Apache5RestCall(
            httpClient,
            loggerAdapter,
            url,
            null,
            MediaType.APPLICATION_JSON,
            null,
            formatter,
            parser,
            null
        );
    }
}
