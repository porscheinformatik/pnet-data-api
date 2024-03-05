package at.porscheinformatik.happyrest.apache;

import java.net.ProxySelector;
import java.time.Duration;

import at.porscheinformatik.happyrest.MediaType;
import at.porscheinformatik.happyrest.RestCall;
import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.RestParser;
import at.porscheinformatik.happyrest.RestUtilsTest;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.jackson.JacksonBasedFormatter;
import at.porscheinformatik.happyrest.jackson.JacksonBasedParser;
import at.porscheinformatik.happyrest.util.ByteArrayParser;
import at.porscheinformatik.happyrest.util.CharArrayParser;
import at.porscheinformatik.happyrest.util.NumberParser;
import at.porscheinformatik.happyrest.util.StringParser;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;

public class MockedApacheRestCallFactory extends ApacheRestCallFactory
{
    public static MockedApacheRestCallFactory createMock()
    {
        RestFormatter formatter =
            RestFormatter.of(new JacksonBasedFormatter(RestUtilsTest.OBJECT_MAPPER), new TextPlainFormatter());
        RestParser parser = RestParser.of(StringParser.INSTANCE, NumberParser.INSTANCE, CharArrayParser.INSTANCE,
            ByteArrayParser.INSTANCE, new JacksonBasedParser(RestUtilsTest.OBJECT_MAPPER));

        return new MockedApacheRestCallFactory(SystemRestLoggerAdapter.INSTANCE, formatter, parser);
    }

    public MockedApacheRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser,
        ProxySelector proxySelector, Duration timeout, String userAgent)
    {
        super(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public MockedApacheRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser)
    {
        super(loggerAdapter, formatter, parser);
    }

    @Override
    protected MockedApacheRestCallFactory copy(RestLoggerAdapter loggerAdapter, RestFormatter formatter,
        RestParser parser, ProxySelector proxySelector, Duration timeout, String userAgent)
    {
        return new MockedApacheRestCallFactory(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    @Override
    public RestCall url(String url)
    {
        return new MockedApacheRestCall(httpClient, loggerAdapter, url, null, MediaType.APPLICATION_JSON, null,
            formatter, parser, null);
    }
}
