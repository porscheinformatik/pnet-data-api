package at.porscheinformatik.happyrest.java;

import java.net.ProxySelector;
import java.time.Duration;

import at.porscheinformatik.happyrest.MediaType;
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

public class MockedJavaRestCallFactory extends JavaRestCallFactory
{
    public static MockedJavaRestCallFactory createMock()
    {
        RestFormatter formatter =
            RestFormatter.of(new JacksonBasedFormatter(RestUtilsTest.OBJECT_MAPPER), new TextPlainFormatter());
        RestParser parser = RestParser
            .of(StringParser.INSTANCE, NumberParser.INSTANCE, CharArrayParser.INSTANCE, ByteArrayParser.INSTANCE,
                new JacksonBasedParser(RestUtilsTest.OBJECT_MAPPER));

        return new MockedJavaRestCallFactory(SystemRestLoggerAdapter.INSTANCE, formatter, parser);
    }

    public MockedJavaRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser,
        ProxySelector proxySelector, Duration timeout, String userAgent)
    {
        super(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    public MockedJavaRestCallFactory(RestLoggerAdapter loggerAdapter, RestFormatter formatter, RestParser parser)
    {
        super(loggerAdapter, formatter, parser);
    }

    @Override
    protected MockedJavaRestCallFactory copy(RestLoggerAdapter loggerAdapter, RestFormatter formatter,
        RestParser parser, ProxySelector proxySelector, Duration timeout, String userAgent)
    {
        return new MockedJavaRestCallFactory(loggerAdapter, formatter, parser, proxySelector, timeout, userAgent);
    }

    @Override
    public MockedJavaRestCall url(String url)
    {
        return new MockedJavaRestCall(httpClient, userAgent, loggerAdapter, url, null, MediaType.APPLICATION_JSON, null,
            formatter, parser, null);
    }
}
