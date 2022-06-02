package pnet.data.api.client.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.client.RestTemplate;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.SpringRestCallFactory;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;

@Configuration
@ComponentScan(basePackageClasses = {ContextPnetDataApiClientConfig.class})
public class ContextPnetDataApiClientConfig extends AbstractContextPnetDataApiClientConfig
{
    @Override
    protected RestCallFactory createSpringRestCallFactory(RestTemplate restTemplate, RestLoggerAdapter loggerAdapter,
        ConversionService conversionService)
    {
        return new SpringRestCallFactory(restTemplate, loggerAdapter, new SpringRestFormatter(conversionService));
    }
}
