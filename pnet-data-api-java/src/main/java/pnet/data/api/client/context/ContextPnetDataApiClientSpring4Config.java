package pnet.data.api.client.context;

import at.porscheinformatik.happyrest.RestCallFactory;
import at.porscheinformatik.happyrest.RestLoggerAdapter;
import at.porscheinformatik.happyrest.spring.Spring4RestCallFactory;
import at.porscheinformatik.happyrest.spring.SpringRestFormatter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = { ContextPnetDataApiClientSpring4Config.class })
@Deprecated
public class ContextPnetDataApiClientSpring4Config extends AbstractContextPnetDataApiClientConfig {

    @Override
    protected RestCallFactory createSpringRestCallFactory(
        RestTemplate restTemplate,
        RestLoggerAdapter loggerAdapter,
        ConversionService conversionService
    ) {
        return new Spring4RestCallFactory(restTemplate, loggerAdapter, new SpringRestFormatter(conversionService));
    }
}
