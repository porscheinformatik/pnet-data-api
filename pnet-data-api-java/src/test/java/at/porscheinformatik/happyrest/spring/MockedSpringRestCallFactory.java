package at.porscheinformatik.happyrest.spring;

import org.springframework.web.client.RestTemplate;

import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;

public class MockedSpringRestCallFactory extends SpringRestCallFactory
{
    public static MockedSpringRestCallFactory createMock()
    {
        return new MockedSpringRestCallFactory(new RestTemplate(), SystemRestLoggerAdapter.INSTANCE,
            new TextPlainFormatter());
    }

    public MockedSpringRestCallFactory(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter,
        RestFormatter formatter)
    {
        super(restTemplate, loggerAdapter, formatter);
    }

    @Override
    public MockedSpringRestCall url(String url)
    {
        return new MockedSpringRestCall(restTemplate, loggerAdapter, formatter, url);
    }
}
