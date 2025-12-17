package at.porscheinformatik.happyrest.spring;

import at.porscheinformatik.happyrest.RestFormatter;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.SystemRestLoggerAdapter;
import at.porscheinformatik.happyrest.util.TextPlainFormatter;
import org.springframework.web.client.RestTemplate;

public class MockedRestTemplateRestCallFactory extends RestTemplateRestCallFactory {

    public static MockedRestTemplateRestCallFactory createMock() {
        return new MockedRestTemplateRestCallFactory(
            new RestTemplate(),
            SystemRestLoggerAdapter.INSTANCE,
            new TextPlainFormatter()
        );
    }

    public MockedRestTemplateRestCallFactory(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        RestFormatter formatter
    ) {
        super(restTemplate, loggerAdapter, formatter);
    }

    @Override
    public MockedRestTemplateRestCall url(String url) {
        return new MockedRestTemplateRestCall(restTemplate, loggerAdapter, formatter, url);
    }
}
