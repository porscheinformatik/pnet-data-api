package at.porscheinformatik.happyrest.apache5;

import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.jackson.JacksonBasedFormatter;
import at.porscheinformatik.happyrest.jackson.JacksonBasedParser;
import at.porscheinformatik.happyrest.util.ByteArrayParser;
import at.porscheinformatik.happyrest.util.CharArrayParser;
import at.porscheinformatik.happyrest.util.NumberParser;
import at.porscheinformatik.happyrest.util.StringParser;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;
import java.net.ProxySelector;
import java.time.Duration;
import tools.jackson.databind.json.JsonMapper;

public class MockedApache5RestCallFactory extends Apache5RestCallFactory {

    private static final JsonMapper JSON_MAPPER = new JsonMapper();

    public static MockedApache5RestCallFactory createMock() {
        RestFormatter formatter = RestFormatter.of(new JacksonBasedFormatter(JSON_MAPPER), new TextPlainFormatter());
        RestParser parser = RestParser.of(
            StringParser.INSTANCE,
            NumberParser.INSTANCE,
            CharArrayParser.INSTANCE,
            ByteArrayParser.INSTANCE,
            new JacksonBasedParser(JSON_MAPPER)
        );

        return new MockedApache5RestCallFactory(SystemRestLoggerAdapter.INSTANCE, formatter, parser);
    }

    public MockedApache5RestCallFactory(
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter,
        RestParser parser,
        ProxySelector proxySelector,
        Duration timeout,
        String userAgent
    ) {
        super(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public MockedApache5RestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser) {
        super(loggerAdapter, formatter, parser);
    }

    @Override
    protected MockedApache5RestCallFactory copy(
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter,
        RestParser parser,
        ProxySelector proxySelector,
        Duration timeout,
        String userAgent
    ) {
        return new MockedApache5RestCallFactory(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    @Override
    public RestCall url(String url) {
        return new MockedApache5RestCall(
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
